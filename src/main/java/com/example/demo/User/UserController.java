package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserModelAssembler userModelAssembler;

  @GetMapping("/users")
  public Page<User> index() {
    Pageable paging = PageRequest.of(0, 10);
    Page<User> users = userRepository.findAll(paging);
    return users;
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
