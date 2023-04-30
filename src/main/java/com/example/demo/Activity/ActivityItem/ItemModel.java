package com.example.demo.Activity.ActivityItem;

import com.example.demo.Item.ItemType;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel extends RepresentationModel<ItemModel> {
  private Long id;
  private String slug;
  private String title;
  private ItemType itemType;
}
