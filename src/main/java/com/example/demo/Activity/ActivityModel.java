package com.example.demo.Activity;

import com.example.demo.User.UserModel;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@Relation(collectionRelation = "activities", itemRelation = "activity")
@JsonInclude(Include.NON_NULL)
public class ActivityModel extends RepresentationModel<ActivityModel> {
  private Long id;
  private java.util.Date createdAt;

  private Verb verb;
  private ItemModel item;
  private UserModel user;
  private boolean completed;
}
