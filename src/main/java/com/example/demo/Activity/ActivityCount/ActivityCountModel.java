package com.example.demo.Activity.ActivityCount;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.example.demo.User.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "activityCounts", itemRelation = "activityCount")
public class ActivityCountModel extends RepresentationModel<ActivityCountModel> {
  private Long activitiesCount;

  private UserModel user;
}
