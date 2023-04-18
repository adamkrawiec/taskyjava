package com.example.demo.Activity;

import java.util.Set;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "verbs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Verb {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String name;

  @OneToMany(mappedBy = "verb")
  private Set<Activity> activities;

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }
}
