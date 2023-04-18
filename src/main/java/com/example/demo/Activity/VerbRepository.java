package com.example.demo.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerbRepository extends JpaRepository<Verb, Long>{
  Verb findByName(String name); 
}
