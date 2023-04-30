package com.example.demo.User.AssignedItemUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.User.User;

public interface AssignedItemUserRepository extends JpaRepository<AssignedItemUser, Long> {
  Page<AssignedItemUser> findByUser(User user, Pageable pageable);
}
