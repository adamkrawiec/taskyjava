package com.example.demo.Item;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.query.sqm.CastType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;

import com.example.demo.Item.ItemTranslation.ItemTranslation;
import com.example.demo.User.User;
import com.example.demo.User.AssignedItemUser.AssignedItemUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.micrometer.common.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

@Entity
@Table(name = "items", uniqueConstraints = { @UniqueConstraint(columnNames = { "slug" })})
@SecondaryTable(name = "item_translations", pkJoinColumns = @PrimaryKeyJoinColumn(name = "item_id", referencedColumnName = "id"))
@EntityListeners(AuditingEntityListener.class)
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NaturalId
  @Column
  private String slug;

  @Column(table = "item_translations", name = "title")
  private String title;

  @Column(table = "item_translations", name = "description")
  private String description;

  @Column(table = "item_translations", name = "locale")
  private String locale;

  @Column(name = "company_id")
  @NotNull(message = "Company ID cannot be blank")
  private Long companyId;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "item_type")
  @NotNull(message = "Item Type cannot be blank")
  private ItemType itemType;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @CreatedDate
  @Column(table = "item_translations", name = "created_at")
  private LocalDateTime translationCreatedAt;

  @LastModifiedDate
  @Column(table = "item_translations", name = "updated_at")
  private LocalDateTime translationUpdatedAt;

  @Column
  @Convert(converter = ItemVisibilityConverter.class)
  private ItemVisibility visibility;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @OneToMany(mappedBy = "item")
  private Set<AssignedItemUser> assignedItemUsers = new HashSet<AssignedItemUser>();

  @OneToMany(mappedBy = "item",
             cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
             orphanRemoval = true)
  private Map<String, ItemTranslation> itemTranslations = new HashMap<>();
  // private Set<ItemTranslation> itemTranslations = new HashSet<>();

  // build translatins table using @Embedded
  public Long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public Long getCompanyId() {
    return this.companyId;
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

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public ItemVisibility getVisibility() {
    return this.visibility;
  }

  public String getDescription() {
    return this.description;
  }

  public Set<AssignedItemUser> getAssignedItemUsers() {
    return this.assignedItemUsers;
  }

  public void setTitile(String title) {
    this.title = title;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Map<String, ItemTranslation> getItemTranslations() {
    return this.itemTranslations;
  }

  public String getLocale() {
    return this.locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }
}
