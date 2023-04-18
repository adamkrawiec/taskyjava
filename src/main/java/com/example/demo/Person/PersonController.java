package com.example.demo.Person;

import java.util.List;
import java.util.stream.Collectors;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;

@RestController
public class PersonController {
  private final PersonRepository personRepository;

  private final PersonModelAssembler personModelAssembler;

  PersonController(PersonRepository personRepository, PersonModelAssembler personModelAssembler) {
    this.personRepository = personRepository;
    this.personModelAssembler = personModelAssembler;
  }

  @GetMapping("/hello_people")
  public CollectionModel<EntityModel<Person>> index() {
    List<EntityModel<Person>> persons = personRepository.findAll()
                                                        .stream()
                                                        .map(person -> personModelAssembler.toModel(person))
                                                        .collect(Collectors.toList());
    
    return CollectionModel.of(persons, linkTo(methodOn(PersonController.class).index()).withSelfRel());
  }

  @GetMapping("hello_people/{personId}")
  public EntityModel<Person> getOne(@PathVariable Long personId) {
      Person person = findOr404(personId);
      return personModelAssembler.toModel(person);
  }

  @PutMapping("hello_people/{personID}")
  ResponseEntity<?> updatePerson(@RequestBody Person newPerson, @PathVariable Long personId) {
    Person person = findOr404(personId);
    person.setFullName(newPerson.getFullName());
    person.setJobTitle(newPerson.getJobTitle());;
    
    EntityModel<Person> entityModel = personModelAssembler.toModel(person);
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
  }

  @PostMapping("/hello_people")
  ResponseEntity<?> createPerson(@RequestBody Person newPerson) {
    EntityModel<Person> entityModel = personModelAssembler.toModel(personRepository.save(newPerson));

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
  }

  @DeleteMapping("hello_people/{personId}")
  ResponseEntity<?> deletePerson(@PathVariable Long personId) {
    personRepository.deleteById(personId);

    return ResponseEntity.noContent().build();
  }

  private Person findOr404(Long personId) {
    return personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
  }
}
