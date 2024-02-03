package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserModelAssembler userModelAssembler;

  @Autowired
  private PagedResourcesAssembler<User> pagedUserResourcesAssembler;

  @GetMapping("/users")
  public PagedModel<UserModel> index(
    @RequestParam(name = "page", defaultValue = "0") int page
  ) {
    Pageable paging = PageRequest.of(page, 10);
    Page<User> users = userRepository.findAll(paging);

    return pagedUserResourcesAssembler.toModel(users, userModelAssembler);
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserModel> getOne(@PathVariable("id") Long id) {
    User user = userRepository.findById(id)
                              .orElseThrow(
                                () -> new ResourceNotFoundException("Not found user with id: " + id)
                              );



    UserModel userModel = userModelAssembler.toModel(user);
    
    return new ResponseEntity<>(userModel, HttpStatus.OK);
  }
}
