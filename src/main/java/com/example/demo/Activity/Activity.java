package com.example.demo.Activity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedAttributeNode;

import com.example.demo.User.User;
import com.example.demo.Item.Item;

import java.io.Serializable;

@NamedEntityGraph(
  name = "Activity.itemUserVerb",
  attributeNodes = {
    @NamedAttributeNode("user"),
    @NamedAttributeNode("verb"),
    @NamedAttributeNode("item")
  }
)
@Entity
@Table(name = "activities")
public class Activity implements Serializable{
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "activityable_type")
  private String activityableType;

  @Column(name = "activityable_id", updatable = false, insertable = false)
  private Long activityableId;

  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date createdAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @Column(name = "user_id", updatable = false, insertable = false)
  private Long userId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "activityable_id", referencedColumnName = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "verb_id", referencedColumnName = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Verb verb;

  @Column(name = "verb_id", updatable = false, insertable = false)
  private Long verbId;

  @Column
  private boolean completed;

  public Long getId() {
    return this.id;
  }

  public String getActivityableType() {
    return this.activityableType;
  }

  public Long getactivityableId() {
    return this.activityableId;
  }

  public Long getUserId() {
    return this.userId;
  }

  public Item getItem() {
    return this.item;
  }

  public Verb getVerb() {
    return this.verb;
  }

  public Long getVerbId() {
    return this.verbId;
  }

  public User getUser() {
    return this.user;
  }

  public boolean getCompleted() {
    return this.completed;
  }

  public java.util.Date getCreatedAt() {
    return this.createdAt;
  }
}
