package com.example.demo.Activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;


public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity>{
  @EntityGraph(value = "Activity.itemUserVerb")
  Page<Activity> findAll(Specification<Activity> specification, Pageable pageable);
}
