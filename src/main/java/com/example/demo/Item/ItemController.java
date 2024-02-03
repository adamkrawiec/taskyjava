package com.example.demo.Item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.web.PagedResourcesAssembler;

import org.springframework.hateoas.PagedModel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

@RestController
public class ItemController {
  @Autowired
  private ItemModelAssembler itemModelAssembler;

  @Autowired
  private PagedResourcesAssembler<Item> pagedResourcesAssembler;
  
  @Autowired
  private ItemService itemService;

  @PostMapping("/items")
  public ItemModel createItem(@Valid @RequestBody Item itemBody) {
    return itemModelAssembler.toModel(itemService.createItem(itemBody));
  }

  @GetMapping("/items")
  public PagedModel<ItemModel> itemsIndex(
    @RequestParam(name = "page", defaultValue = "0") int page
  ) {
    Pageable pageable = PageRequest.of(page, 10);

    Page<Item> items = itemService.getItems(pageable);
    return pagedResourcesAssembler.toModel(items, itemModelAssembler);
  };

  @GetMapping("/items/id/{itemId}")
  public ItemModel getItem(@PathVariable Long itemId) {
    return itemModelAssembler.toModel((itemService.getItem(itemId)));
  }

  @GetMapping("/items/{itemSlug}")
  public ItemModel getItemBySlug(@PathVariable String itemSlug) {
    return itemModelAssembler.toModel((itemService.getItem(itemSlug)));
  }
}
