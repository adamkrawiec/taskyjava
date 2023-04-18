package com.example.demo.Activity.ActivityCount;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.example.demo.Activity.ActivityController;
import com.example.demo.User.User;
import com.example.demo.User.UserModel;
import com.example.demo.User.UserModelAssembler;
import com.example.demo.User.UserRepository;

@Component
public class ActivityCountModelAssembler extends RepresentationModelAssemblerSupport<ActivityCount, ActivityCountModel> {
  
  @Autowired
  UserRepository userRepository;

  @Autowired
  UserModelAssembler userModelAssembler;
  
  public ActivityCountModelAssembler() {
    super(ActivityController.class, ActivityCountModel.class);
  }

  @Override
  public ActivityCountModel toModel(ActivityCount activityCount) {
    ActivityCountModel activityCountModel = instantiateModel(activityCount);

    activityCountModel.setUser(findUser(activityCount.getUserId()));
    activityCountModel.setActivitiesCount(activityCount.getActivitiesCount());
    return activityCountModel;
  }

  private UserModel findUser(Long userId) {
    Optional<User> user = userRepository.findById(userId);
    return userModelAssembler.toModel(user.get());
  }
}
