package com.example.demo.Person;

public class PersonNotFoundException extends RuntimeException {
  PersonNotFoundException(Long id) {
    super("Could not find employee " + id);
  }
}
