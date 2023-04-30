package com.example.demo.Item.UserModel;

import com.example.demo.User.UserController;
import com.example.demo.User.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ItemUserModelAssembler extends RepresentationModelAssemblerSupport<User, ItemUserModel>{
  public ItemUserModelAssembler() {
    super(UserController.class, ItemUserModel.class);
  }

  public ItemUserModel toModel(User user) {
    if(user == null) {
      return null;
    }

    ItemUserModel itemUserModel = instantiateModel(user);

    itemUserModel.setId(user.getId());
    itemUserModel.setFirstName(user.getFirstName());
    itemUserModel.setLastName(user.getLastName());
  
    itemUserModel.add(linkTo(
      methodOn(UserController.class).getOne(user.getId()))
      .withSelfRel()
    );

    return itemUserModel;
  }
}
