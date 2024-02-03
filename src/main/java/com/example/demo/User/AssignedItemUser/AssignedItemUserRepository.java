package com.example.demo.User.AssignedItemUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.User.User;

public interface AssignedItemUserRepository extends JpaRepository<AssignedItemUser, Long> {
  @EntityGraph(value = "AssignedItemUser.itemUser")
  Page<AssignedItemUser> findByUser(User user, Pageable pageable);
}
