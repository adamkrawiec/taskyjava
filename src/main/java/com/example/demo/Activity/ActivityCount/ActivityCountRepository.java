package com.example.demo.Activity.ActivityCount;

import com.example.demo.Activity.Activity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityCountRepository extends JpaRepository<Activity, Long> {
  @Query(value = "SELECT activities.user_id as userId, count(activities.*) as activitiesCount from activities where activities.activityable_type = 'Item' AND activities.activityable_id = :itemId GROUP BY activities.user_id ORDER BY activitiesCount DESC", nativeQuery = true)
  List<ActivityCount> countActivitiesByUser(Long itemId);
}
