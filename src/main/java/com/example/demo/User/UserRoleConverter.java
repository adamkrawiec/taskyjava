package com.example.demo.User;

import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;

@Converter
public class UserRoleConverter  implements AttributeConverter<UserRole, Integer>{
  
  @Override
  public Integer convertToDatabaseColumn(UserRole role) {
    if(role == null) {
      return null;
    }

    switch(role) {
      case VIEWER:
        return 0;
      case CURATOR:
        return 1;
      case REPORTER:
        return 4;
      case LEARNING_DESIGNER:
        return 6;
      case HR:
        return 4;
      case ADMIN:
        return 2;
      case OWNER:
        return 3;
      case SUPER_ADMIN:
        return 10;
      default:
      throw new IllegalArgumentException(role + " not supported.");
    }
  }

  @Override
  public UserRole convertToEntityAttribute(Integer roleInt) {
    if(roleInt == null) {
      return null;
    }

    switch(roleInt) {
      case 0:
        return UserRole.VIEWER;
      case 1:
        return UserRole.CURATOR;
      case 2:
        return UserRole.ADMIN;
      case 3:
        return UserRole.OWNER;
      case 4:
        return UserRole.REPORTER;
      case 5:
        return UserRole.HR;
      case 6:
        return UserRole.LEARNING_DESIGNER;
      case 10:
        return UserRole.SUPER_ADMIN;
      default:
      throw new IllegalArgumentException(roleInt + " not supported.");
    }
  }
}
