package com.example.demo.Item;

import com.example.demo.Activity.ActivityController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class ItemModelAssembler extends RepresentationModelAssemblerSupport<Item, ItemModel>{
  public ItemModelAssembler(){
    super(ItemController.class, ItemModel.class);
  }

  @Autowired
  ItemUserModelAssembler itemUserModelAssembler;

  @Override
  public ItemModel toModel(Item item) {
    ItemModel itemModel = instantiateModel(item);

    itemModel.setId(item.getId());
    itemModel.setTitle(item.getTitle());
    itemModel.setDescription(item.getDescription());
    itemModel.setItemType(item.getItemType());
    itemModel.setSlug(item.getSlug());
    itemModel.setCreatedAt(item.getCreatedAt());
    itemModel.setVisibility(item.getVisibility());
    itemModel.setUser(itemUserModelAssembler.toModel(item.getUser()));

    itemModel.add(selfRel(item));
    itemModel.add(activitiesLink(item));
    itemModel.add(activitiesLeaderboardLink(item));

    return itemModel;
  }

  private Link selfRel(Item item) {
    return linkTo(
      methodOn(ItemController.class).getItemBySlug(item.getSlug())
    ).withSelfRel();
  }

  private Link activitiesLink(Item item) {
    return linkTo(
      methodOn(ActivityController.class).getItemActivities(
        item.getId(),
        0
      )
    )
    .withRel("activities");
  }

  private Link activitiesLeaderboardLink(Item item) {
    return linkTo(
      methodOn(ActivityController.class).getItemLeaderboard(
        item.getId()
      )
    ).withRel("leaderboard");
  }
}
