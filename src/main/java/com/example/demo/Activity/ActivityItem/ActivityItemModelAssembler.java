package com.example.demo.Activity.ActivityItem;

import com.example.demo.Item.Item;
import com.example.demo.Item.ItemController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ActivityItemModelAssembler extends RepresentationModelAssemblerSupport<Item, ItemModel> {
  public ActivityItemModelAssembler() {
    super(ItemController.class, ItemModel.class);
  }
  
  @Override
  public ItemModel toModel(Item item) {
    ItemModel itemModel = instantiateModel(item);

    itemModel.setId(item.getId());
    itemModel.setSlug(item.getSlug());
    itemModel.setTitle(item.getTitle());
    itemModel.setItemType(item.getItemType());

    itemModel.add(
      linkTo(
        methodOn(ItemController.class).getItem(item.getId())
      ).withSelfRel()
    );

    return itemModel;
  }
}
