package com.example.demo.Activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface ActivityService {
  public Page<Activity> filterActivitiesForItem(
    Pageable pageable,
    Long itemId,
    Map<String, String> filterParams
  );

  public Page<Activity> filterActivitiesForUser(
    Pageable pageable,
    Long userId,
    Map<String, String> filterParams
  );
}
