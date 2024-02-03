package com.example.demo.Item;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

@Component
public interface ItemService {
  public Item createItem(Item itemBody);

  public Page<Item> getItems(Pageable pageable);

  public Item getItem(Long itemId);

  public Item getItem(String slug);
}
