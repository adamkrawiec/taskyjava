package com.example.demo.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class ActivityServiceImpl implements ActivityService {
  @Autowired
  private ActivityRepository activityRepository;

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
    Optional<Long> itemId,
    Optional<Long> userId,
    Optional<Long> verbId,
    Optional<Boolean> completed
  ) {

    if(itemId.isPresent()) {
      activitiesSpecifications = itemIdSpecifivation(activitiesSpecifications, itemId.get());
    }

    if(verbId.isPresent()) {
      activitiesSpecifications = verbIdSpecifivation(activitiesSpecifications, verbId.get());
    }

    if(userId.isPresent()) {
      activitiesSpecifications = userIdSpecifivation(activitiesSpecifications, userId.get());
    }

    if(completed.isPresent()) {
      activitiesSpecifications = completedSpecifivation(activitiesSpecifications);
    }

    return activitiesSpecifications;
  }

  public Page<Activity> filterActivitiesForItem(
    Pageable pageable,
    Long itemId,
    Optional<Long> userId,
    Optional<Long> verbId,
    Optional<Boolean> completed
  ) {
    Specification<Activity> activitiesSpecifications = ActivitySpecifications.getActivitiesForItem(itemId);
    
    activitiesSpecifications = commonActivitiesFilters(
      activitiesSpecifications,
      Optional.ofNullable(null),
      userId,
      verbId,
      completed
    );
    
    return activityRepository.findAll(activitiesSpecifications, pageable);
  }

  public Page<Activity> filterActivitiesForUser(
    Pageable pageable,
    Long userId
  ) {
    Specification<Activity> activitiesSpecifications = ActivitySpecifications.getActivitiesForItems();
    activitiesSpecifications = commonActivitiesFilters(
      activitiesSpecifications,
      Optional.ofNullable(null),
      Optional.ofNullable(userId),
      Optional.ofNullable(null),
      Optional.ofNullable(null)
    );
    
    return activityRepository.findAll(activitiesSpecifications, pageable);
  }

  public Page<Activity> filterActivitiesForUser(
    Pageable pageable,
    Long userId,
    Optional<Long> itemId,
    Optional<Long> verbId,
    Optional<Boolean> completed
  ) {
    Specification<Activity> activitiesSpecifications = ActivitySpecifications.getActivitiesForItems();
    activitiesSpecifications = commonActivitiesFilters(
      activitiesSpecifications,
      itemId,
      Optional.ofNullable(userId),
      verbId,
      completed
    );
    
    return activityRepository.findAll(activitiesSpecifications, pageable);
  }
}