package com.example.demo.Item;

import java.util.Date;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel extends RepresentationModel<ItemModel>{
  private Long id;
  private String slug;
  private String title;
  private String description;
  private ItemType itemType;
  private Date createdAt;
  private ItemVisibility visibility;
  private ItemUserModel user;
}
