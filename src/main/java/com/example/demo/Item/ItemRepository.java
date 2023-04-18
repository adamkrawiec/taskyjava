package com.example.demo.Item;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
  public Optional<Item> findBySlug(String slug);
}
