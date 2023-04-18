package com.example.demo.Activity;

import com.example.demo.Activity.ActivityCount.*;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import io.micrometer.common.lang.Nullable;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.*;
import org.springframework.data.web.PagedResourcesAssembler;

import org.springframework.web.bind.annotation.*;

import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class ActivityController {
  @Autowired
  private ActivityCountRepository activityCountRepository;

  @Autowired
  private ActivityCountModelAssembler activityCountModelAssembler;

  @Autowired
  private ActivityService activityService;

  @Autowired
  private ActivityModelAssembler activityModelAssembler;

  @Autowired
  private PagedResourcesAssembler<Activity> pagedResourcesAssembler;  
  
  @Autowired
  private VerbRepository verbRepository;

  @GetMapping("/verbs")
  public List<Verb> verbs() {
    return verbRepository.findAll();
  }

  @GetMapping(path = "/activities/user/{userId}")
  public PagedModel<ActivityModel> userActivities(
    @PathVariable(name = "userId") Long userId,
    @RequestParam(name = "page", defaultValue = "0") int page,
    @Nullable @RequestParam(name = "verbId") Optional<Long> verbId,
    @Nullable @RequestParam(name = "itemId") Optional<Long> itemId,
    @Nullable @RequestParam(name = "completed") Optional<Boolean> completed
  ) {
    Pageable pageable = PageRequest.of(page, 10);

    Page<Activity> activities = activityService.filterActivitiesForUser(pageable, userId, itemId, verbId, completed);


    return pagedResourcesAssembler.toModel(activities, activityModelAssembler);
  }

  @GetMapping(path = "/activities/item/{itemId}")
  public PagedModel<ActivityModel> getItemActivities(
    @PathVariable(name = "itemId") Long itemId,
    @RequestParam(name = "page", defaultValue = "0") int page,
    @Nullable @RequestParam(name = "verbId") Optional<Long> verbId,
    @Nullable @RequestParam(name = "userId") Optional<Long> userId,
    @Nullable @RequestParam(name = "completed") Optional<Boolean> completed
  ) {
    Pageable pageable = PageRequest.of(page, 20);
    
    Page<Activity> activities = activityService.filterActivitiesForItem(pageable, itemId, userId, verbId, completed);

    return pagedResourcesAssembler.toModel(activities, activityModelAssembler);
  }

  @GetMapping("/activities/item/{itemId}/leaderboard")
  public ResponseEntity<?> getItemLeaderboard(@PathVariable Long itemId) {
    List<ActivityCountModel> activities = activityCountRepository
      .countActivitiesByUser(itemId)
      .stream()
      .map(activityCount -> activityCountModelAssembler.toModel(activityCount))
      .collect(Collectors.toList());
    
    return new ResponseEntity<>(activities, HttpStatus.OK);
  }
}
