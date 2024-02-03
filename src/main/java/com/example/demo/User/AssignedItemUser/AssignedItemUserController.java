package com.example.demo.User.AssignedItemUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.PagedModel;

import com.example.demo.User.User;
import com.example.demo.User.UserRepository;

@RestController
public class AssignedItemUserController {
  @Autowired
  private PagedResourcesAssembler<AssignedItemUser> pagedResourcesAssembler;

  @Autowired
  private AssignedItemUserModelAssembler assignedItemUserModelAssembler;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AssignedItemUserRepository assignedItemUserRepository;

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
