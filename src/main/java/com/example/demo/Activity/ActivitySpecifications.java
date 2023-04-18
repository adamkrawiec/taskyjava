package com.example.demo.Activity;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;


public class ActivitySpecifications {
  private static Predicate itemsPredicate(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    return builder.equal(root.get(Activity_.activityableType), "Item");
  }

  public static Specification<Activity> getActivitiesCompleted() {  
    return new Specification<Activity>() {
      @Override
      public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.isTrue(root.get(Activity_.completed));
      }
    };
  }

  public static Specification<Activity> getActivitiesForItems() {
    return new Specification<Activity>() {
      @Override
      public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return itemsPredicate(root, query, builder);
      }
    };
  }

  public static Specification<Activity> getActivitiesForItem(Long itemId) {
    return new Specification<Activity>() {
      @Override
      public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate itemsActivities = itemsPredicate(root, query, builder);
        Predicate itemActivities = builder.equal(root.get(Activity_.activityableId), itemId);

        return builder.and(itemsActivities, itemActivities);
      }
    };
  }

  public static Specification<Activity> getActivitiesWithVerbId(Long verbId) {
    return new Specification<Activity>() {
      @Override
      public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.equal(root.get(Activity_.verbId), verbId);
      }
    };
  }

  public static Specification<Activity> getActivitiesForUser(Long userId) {
    return new Specification<Activity>() {
      @Override
      public Predicate toPredicate(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.equal(root.get(Activity_.userId), userId);
      }
    };
  }


}
