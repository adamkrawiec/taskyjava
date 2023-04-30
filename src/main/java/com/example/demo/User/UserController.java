package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.User.AssignedItemUser.AssignedItemUser;
import com.example.demo.User.AssignedItemUser.AssignedItemUserModel;
import com.example.demo.User.AssignedItemUser.AssignedItemUserModelAssembler;
import com.example.demo.User.AssignedItemUser.AssignedItemUserRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserModelAssembler userModelAssembler;

  @Autowired
  private PagedResourcesAssembler<AssignedItemUser> pagedResourcesAssembler;

  @Autowired
  private PagedResourcesAssembler<User> pagedUserResourcesAssembler;

  @Autowired
  private AssignedItemUserModelAssembler assignedItemUserModelAssembler;

  @Autowired
  private AssignedItemUserRepository assignedItemUserRepository;

  @GetMapping("/users")
  public PagedModel<UserModel> index() {
    Pageable paging = PageRequest.of(0, 10);
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

  @GetMapping("/users/{id}/assigned_items")
  public PagedModel<AssignedItemUserModel> assingedItems(
    @PathVariable("id") Long id,
    @RequestParam(name = "page", defaultValue = "0") int page
  ) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user with id: " + id));
    
    Pageable pageable = PageRequest.of(page, 10);

    Page<AssignedItemUser> assingedPage = assignedItemUserRepository.findByUser(user, pageable);

    return pagedResourcesAssembler.toModel(assingedPage, assignedItemUserModelAssembler);
  }

}
