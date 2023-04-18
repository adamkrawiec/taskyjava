package com.example.demo.Person;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {
  @Override
  public EntityModel<Person> toModel(Person person) {
    return EntityModel.of(person,
    linkTo(methodOn(PersonController.class).getOne(person.getId())).withSelfRel(),
    linkTo(methodOn(PersonController.class).index()).withRel("people"));
  }
}
