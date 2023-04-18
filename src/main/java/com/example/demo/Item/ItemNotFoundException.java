package com.example.demo.Item;

public class ItemNotFoundException extends RuntimeException {
  ItemNotFoundException(Long id) {
    super("Could not find item with id: " + id);
  }

  ItemNotFoundException(String slug) {
    super("Could not find item with slug: " + slug);
  }
}
