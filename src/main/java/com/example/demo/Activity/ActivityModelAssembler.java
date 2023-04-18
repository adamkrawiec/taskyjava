package com.example.demo.Activity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Item.ItemController;
import com.example.demo.User.UserController;
import com.example.demo.User.UserModelAssembler;

@Component
public class ActivityModelAssembler extends RepresentationModelAssemblerSupport<Activity, ActivityModel>{
  public ActivityModelAssembler() {
    super(ActivityController.class, ActivityModel.class);
  };

  @Autowired
  UserModelAssembler userModelAssembler;

  @Autowired
  ActivityItemModelAssembler itemModelAssembler;
  
  @Override
  public ActivityModel toModel(Activity activity) {
    ActivityModel activityModel = instantiateModel(activity);
    
    activityModel.setId(activity.getId());
    activityModel.setCreatedAt(activity.getCreatedAt());
    activityModel.setItem(itemModelAssembler.toModel(activity.getItem()));
    activityModel.setUser(userModelAssembler.toModel(activity.getUser()));
    activityModel.setVerb(activity.getVerb());
    activityModel.setCompleted(activity.getCompleted());
    
    activityModel.add(linkTo(
      methodOn(UserController.class)
      .getOne(activity.getUserId()))
      .withRel("user")
    );

    activityModel.add(linkTo(
      methodOn(ItemController.class)
      .getItem(activity.getItem().getId()))
      .withRel("item")
    );
    
      return activityModel;
  }
}
