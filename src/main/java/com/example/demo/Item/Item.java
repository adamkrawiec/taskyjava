package com.example.demo.Item;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import org.hibernate.annotations.NaturalId;

import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@Entity
@Table(name = "items", uniqueConstraints = { @UniqueConstraint(columnNames = { "slug" })})
@SecondaryTable(name = "item_translations", pkJoinColumns = @PrimaryKeyJoinColumn(name = "item_id", referencedColumnName = "id"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item {
  @Id
  @GeneratedValue
  private long id;

  @NaturalId
  @Column
  private String slug;

  @Column(table = "item_translations", name = "title")
  private String title;

  @Column(table = "item_translations", name = "description")
  private String description;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "item_type")
  private ItemType itemType;

  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @Column
  private ItemVisibility visibility;

  public Long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public ItemType getItemType() {
    return this.itemType;
  }

  public String getSlug() {
    return this.slug;
  }

  public User getUser() {
    return this.user;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public ItemVisibility getVisibility() {
    return this.visibility;
  }

  public String getDescription() {
    return this.description;
  }
}
