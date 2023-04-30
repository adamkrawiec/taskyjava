package com.example.demo.User.AssignedItemUser;

import com.example.demo.Item.ItemModel;
import com.example.demo.User.UserModel;
import org.springframework.hateoas.server.core.Relation;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "assigned_item_users", itemRelation = "assigned_item_user")
public class AssignedItemUserModel extends RepresentationModel<AssignedItemUserModel>{
  private UserModel user;
  private ItemModel item;
}
