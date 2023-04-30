package com.example.demo.User.AssignedItemUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.demo.Item.ItemModelAssembler;
import com.example.demo.User.UserController;
import com.example.demo.User.UserModelAssembler;

@Component
public class AssignedItemUserModelAssembler extends RepresentationModelAssemblerSupport<AssignedItemUser, AssignedItemUserModel> {
  @Autowired
  ItemModelAssembler itemModelAssembler;

  @Autowired
  UserModelAssembler userModelAssembler;

  public AssignedItemUserModelAssembler() {
    super(UserController.class, AssignedItemUserModel.class);
  }

  @Override
  public AssignedItemUserModel toModel(AssignedItemUser assignedItemUser) {
    AssignedItemUserModel assignedItemUserModel = instantiateModel(assignedItemUser);

    assignedItemUserModel.setItem(itemModelAssembler.toModel(assignedItemUser.getItem()));
    assignedItemUserModel.setUser(userModelAssembler.toModel(assignedItemUser.getUser()));


    return assignedItemUserModel;
  }
}
