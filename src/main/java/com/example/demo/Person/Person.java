package com.example.demo.Person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Node
public class Person {
  private @Id @GeneratedValue Long id;
  private String fullName;
  private String jobTitle;

  private Person() {

  }

  public Person(String fullName, String jobTitle) {
    this.fullName = fullName;
    this.jobTitle = jobTitle;
  }

  public Long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public String getBio() {
    return fullName + "[" + jobTitle + "]";
  }

  public Set<Person> getTeammates() {
    return teammates;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  @Relationship(type = "TEAMMATE")
  public Set<Person> teammates;

  public void worksWith(Person person) {
    if(teammates == null) {
      teammates = new HashSet<>();
    }

    teammates.add(person);
  }

  public String toString() {
    return this.fullName + "'s teammates: " + Optional.ofNullable(this.teammates).orElse(Collections.emptySet())
                                                      .stream()
                                                      .map(Person::getBio)
                                                      .collect(Collectors.toList());
  }
}
