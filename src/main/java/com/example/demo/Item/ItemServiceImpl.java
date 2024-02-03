package com.example.demo.Item;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.demo.utils.Slug;

@Service
public class ItemServiceImpl implements ItemService {
  @Autowired
  private ItemRepository itemRepository;

  public Item createItem(Item itemBody) {
    LocaleContextHolder.setLocale(new Locale.Builder().setLanguage("en").build());

    itemBody.setLocale(LocaleContextHolder.getLocale().getLanguage());
    itemBody.setSlug(Slug.toSlug(itemBody.getTitle()));
    Item item = itemRepository.save(itemBody);
    return item;
  }

  public Page<Item> getItems(Pageable pageable) {
    return itemRepository.findAll(pageable);
  }

  public Item getItem(Long itemId) {
    return itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
  }

  public Item getItem(String slug) {
    return itemRepository.findBySlug(slug).orElseThrow(() -> new ItemNotFoundException(slug));
  } 
}
