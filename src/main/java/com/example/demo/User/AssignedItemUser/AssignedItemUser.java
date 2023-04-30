package com.example.demo.User.AssignedItemUser;

import jakarta.persistence.*;

import java.math.BigInteger;

import com.example.demo.Item.Item;
import com.example.demo.User.User;


@Entity
@Table(name = "new_assigned_item_users")
public class AssignedItemUser {
  @Id
  private BigInteger id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  User user;

  @ManyToOne
  @JoinColumn(name = "item_id")
  Item item;

  public Item getItem() {
    return this.item;
  }

  public User getUser() {
    return this.user;
  }
}
