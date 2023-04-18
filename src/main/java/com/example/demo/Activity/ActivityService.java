package com.example.demo.Activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ActivityService {
  public Page<Activity> filterActivitiesForItem(
    Pageable pageable,
    Long itemId,
    Optional<Long> userId,
    Optional<Long> verbId,
    Optional<Boolean> completed
  );

  public Page<Activity> filterActivitiesForUser(
    Pageable pageable,
    Long userId
  );

  public Page<Activity> filterActivitiesForUser(
    Pageable pageable,
    Long userId,
    Optional<Long> itemId,
    Optional<Long> verbId,
    Optional<Boolean> completed
  );
}
