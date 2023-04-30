package com.example.demo.Item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.utils.Slug;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.web.PagedResourcesAssembler;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.PagedModel;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

@RestController
public class ItemController {
  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private ItemModelAssembler itemModelAssembler;

  @Autowired
  private PagedResourcesAssembler<Item> pagedResourcesAssembler;
  
  @PostMapping("/items")
  public ItemModel createItem(@Valid @RequestBody Item itemBody) {
    LocaleContextHolder.setLocale(new Locale.Builder().setLanguage("en").build());

    itemBody.setLocale(LocaleContextHolder.getLocale().getLanguage());
    itemBody.setSlug(Slug.toSlug(itemBody.getTitle()));
    Item item = itemRepository.save(itemBody);

    return itemModelAssembler.toModel(item);
  }

  @GetMapping("/items")
  public PagedModel<ItemModel> itemsIndex(
    @RequestParam(name = "page", defaultValue = "0") int page
  ) {
    Pageable pageable = PageRequest.of(page, 10);

    Page<Item> items = itemRepository.findAll(pageable);
    return pagedResourcesAssembler.toModel(items, itemModelAssembler);
  };

  @GetMapping("/items/id/{itemId}")
  public ItemModel getItem(@PathVariable Long itemId) {
    Item item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
    ItemModel itemModel = itemModelAssembler.toModel((item));
    
    return itemModel;
  }

  @GetMapping("/items/{itemSlug}")
  public ItemModel getItemBySlug(@PathVariable String itemSlug) {
    Item item = itemRepository.findBySlug(itemSlug).orElseThrow(() -> new ItemNotFoundException(itemSlug));
    ItemModel itemModel = itemModelAssembler.toModel((item));
    
    return itemModel;
  }
}
