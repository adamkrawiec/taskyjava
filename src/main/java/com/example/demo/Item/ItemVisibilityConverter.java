package com.example.demo.Item;

import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;

@Converter
public class ItemVisibilityConverter implements AttributeConverter<ItemVisibility, Integer>{
  
  @Override
  public Integer convertToDatabaseColumn(ItemVisibility visibility) {
    if(visibility == null) {
      return null;
    }

    switch(visibility) {
      case ENTIRE_COMPANY:
        return 0;
      case SELECTED:
        return 3;
      case HIDDEN:
        return 2;
      default:
      throw new IllegalArgumentException(visibility + " not supported.");
    }
  }

  @Override
  public ItemVisibility convertToEntityAttribute(Integer visibilityInt) {
    if(visibilityInt == null) {
      return null;
    }

    switch(visibilityInt) {
      case 0:
        return ItemVisibility.ENTIRE_COMPANY;
      case 2:
        return ItemVisibility.HIDDEN;
      case 3:
        return ItemVisibility.SELECTED;
      default:
      throw new IllegalArgumentException(visibilityInt + " not supported.");
    }
  }
}
