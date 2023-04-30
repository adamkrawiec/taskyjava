package com.example.demo.Item;

import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.hateoas.RepresentationModel;

import com.example.demo.Item.UserModel.ItemUserModel;

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
  private LocalDateTime createdAt;
  private ItemVisibility visibility;
  private ItemUserModel user;
}
