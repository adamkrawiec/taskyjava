package com.example.demo.User;

import com.example.demo.Activity.ActivityController;
import com.example.demo.User.AssignedItemUser.AssignedItemUserController;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {
  public UserModelAssembler() {
    super(UserController.class, UserModel.class);
  }

  @Override
  public UserModel toModel(User user) {
    return assembleUserModel(user);
  }

  private UserModel assembleUserModel(User user) {
    UserModel userModel = instantiateModel(user);

    userModel.setId(user.getId());
    userModel.setFirstName(user.getFirstName());
    userModel.setLastName(user.getLastName());
    userModel.setJobTitle(user.getJobTitle());
    userModel.setRole(user.getRole());

    
    userModel.add(linkTo(
      methodOn(UserController.class)
      .getOne(user.getId()))
      .withSelfRel()
    );

    if(user.getManagerId() != null){
      userModel.add(
        linkTo(
          methodOn(UserController.class).getOne(user.getManagerId())
        ).withRel("manager")
      );
    }

    userModel.add(
      linkTo(
        methodOn(ActivityController.class).userActivities(user.getId(), 0, null )
      ).withRel("activities")
    );

    userModel.add(
      linkTo(
        methodOn(AssignedItemUserController.class).assingedItems(user.getId(), 0)
      ).withRel("assigned_items")
    );

    return userModel;
  }
}
