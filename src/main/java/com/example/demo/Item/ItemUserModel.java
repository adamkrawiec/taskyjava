package com.example.demo.Item;

import com.example.demo.User.User;
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
public class ItemUserModel extends RepresentationModel<ItemUserModel>{
  private Long id;
  private String firstName;
  private String lastName;
}
