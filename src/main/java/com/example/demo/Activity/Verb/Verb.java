package com.example.demo.Activity.Verb;

import java.util.Set;
import jakarta.persistence.*;

import com.example.demo.Activity.Activity;
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
