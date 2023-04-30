package com.example.demo.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
class ActivityServiceImpl implements ActivityService {
  @Autowired
  private ActivityRepository activityRepository;

  public Page<Activity> filterActivitiesForItem(
    Pageable pageable,
    Long itemId,
    Map<String, String> filterParams
  ) {
    Specification<Activity> activitiesSpecifications = ActivitySpecifications.getActivitiesForItem(itemId);
    
    activitiesSpecifications = commonActivitiesFilters(
      activitiesSpecifications,
      filterParams
    );
    
    return activityRepository.findAll(activitiesSpecifications, pageable);
  }

  public Page<Activity> filterActivitiesForUser(
    Pageable pageable,
    Long userId,
    Map<String, String> filterParams
  ) {
    Specification<Activity> activitiesSpecifications = ActivitySpecifications.getActivitiesForItems();
    activitiesSpecifications = commonActivitiesFilters(
      activitiesSpecifications,
      filterParams
    );
    
    return activityRepository.findAll(activitiesSpecifications, pageable);
  }

  private Specification<Activity> verbIdSpecifivation(
    Specification<Activity> activitiesSpecifications,
    Long verbId
  ) {
    return activitiesSpecifications.and(
      ActivitySpecifications.getActivitiesWithVerbId(verbId)
    );
  }

  private Specification<Activity> userIdSpecifivation(
    Specification<Activity> activitiesSpecifications,
    Long userId
  ) {
    return activitiesSpecifications.and(
      ActivitySpecifications.getActivitiesForUser(userId)
    );
  }

  private Specification<Activity> itemIdSpecifivation(
    Specification<Activity> activitiesSpecifications,
    Long itemId
  ) {
    return activitiesSpecifications.and(
      ActivitySpecifications.getActivitiesForItem(itemId)
    );
  }

  private Specification<Activity> completedSpecifivation(
    Specification<Activity> activitiesSpecifications
  ) {
    return activitiesSpecifications.and(
      ActivitySpecifications.getActivitiesCompleted()
    );
  }

  private Specification<Activity> commonActivitiesFilters(
    Specification<Activity> activitiesSpecifications,
    Map<String, String> filterParams
  ) {

    if(filterParams.get("itemId") != null) {
      activitiesSpecifications = itemIdSpecifivation(activitiesSpecifications, Long.parseLong(filterParams.get("itemId")));
    }

    if(filterParams.get("verbId") != null) {
      activitiesSpecifications = verbIdSpecifivation(activitiesSpecifications, Long.parseLong(filterParams.get("verbId")));
    }

    if(filterParams.get("userId") != null) {
      activitiesSpecifications = userIdSpecifivation(activitiesSpecifications, Long.parseLong(filterParams.get("userId")));
    }

    if(filterParams.get("completed") != null) {
      activitiesSpecifications = completedSpecifivation(activitiesSpecifications);
    }

    return activitiesSpecifications;
  }
}