package com.example.demo.User;

import com.example.demo.Activity.Activity;
import com.example.demo.User.Role.UserRole;
import com.example.demo.User.Role.UserRoleConverter;
import com.example.demo.User.AssignedItemUser.AssignedItemUser;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  private Long id;
  
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "job_title")
  private String jobTitle;

  @Column(name = "email")
  private String email;

  @Column(name = "role")
  @Convert(converter = UserRoleConverter.class)
  private UserRole role;

  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(name = "manager")
  private Boolean isManager;

  @Column(name = "manager_id", updatable = false, insertable = false)
  private Long managerId;

  @ManyToOne(optional = true)
  @JoinColumn(name = "manager_id", referencedColumnName = "id")
  // write custom on delete set to null
  private User manager;

  @OneToMany(mappedBy = "user")
  private Set<Activity> activities;


  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<AssignedItemUser> assignedItemUsers = new HashSet<AssignedItemUser>();
  
  public User() {

  } 
  
  public User(String firstName, String lastName, String jobTitle, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.jobTitle = jobTitle;
    this.email = email;
  }

  public Long getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }


  public String getLastName() {
    return this.lastName;
  }

  public String getJobTitle() {
    return this.jobTitle;
  }
  
  public String getEmail() {
    return this.email;
  }

  public UserRole getRole() {
    return this.role;
  }

  public String getFullName() {
    return this.firstName + " " + this.lastName + " [" + this.jobTitle + "]";
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public Boolean getIsManager() {
    return this.isManager;
  }

  public User getManager() {
    return this.manager;
  }
  
  public Long getManagerId() {
    return this.managerId;
  }
  
  public Set<Activity> getActivities() {
    return this.activities;
  }

  public Set<AssignedItemUser> getAssignedItemUsers() {
    return this.assignedItemUsers;
  }
}


// => User(
  // id: integer, 
  // email: string, 
  // encrypted_password: string, 
  // reset_password_token: string, 
  // reset_password_sent_at: datetime, 
  // remember_created_at: datetime, 
  // sign_in_count: integer, 
  // current_sign_in_at: datetime, 
  // last_sign_in_at: datetime, 
  // current_sign_in_ip: inet, 
  // last_sign_in_ip: inet, 
  // created_at: datetime,
  // updated_at: datetime,
  // role: integer, 
  // first_name: string, 
  // last_name: string, 
  // invitation_token: string, 
  // invitation_created_at: datetime, 
  // invitation_sent_at: datetime,
  // invitation_accepted_at: datetime, 
  // invitation_limit: integer, 
  // invited_by_id: integer, 
  // invited_by_type: string, 
  // job_title: string, 
  // company_id: integer, 
  // team_id: integer, 
  // accepted_terms: boolean, 
  // taggings_count: integer, 
  // newsletters_subscribed: boolean, 
  // language: string, 
  // company_digest_subscribed: boolean, 
  // team_digest_subscribed: boolean, 
  // user_digest_subscribed: boolean, 
  // invited_count: integer, 
  // manager: boolean, 
  // auto_accepted_by_admin: boolean, 
  // removed_from_company_id: integer, 
  // documents_count: integer, 
  // notes_count: integer, 
  // time_zone: string, 
  // deactivated_at: datetime, 
  // send_invitation_time: datetime, 
  // department_id: integer, 
  // title: string,
  //  hibob_id: string, 
  //  failed_attempts: integer, 
  //  locked_at: datetime, 
  //  password_changed_at: datetime, 
  //  coach_id: integer, 
  //  is_coach: boolean, 
  //  editor: boolean, 
  //  previous_lock: datetime, 
  //  permanent_lock: boolean, 
  //  manager_id: integer, 
  //  unique_session_id: string, 
  //  current_device_name: string, 
  //  recommendations_filters: jsonb, 
  //  carousel_completion_notice_displayed: boolean, 
  //  learnlist_completion_notice_displayed: boolean, 
  //  password_request_count: integer, 
  //  password_reset_count: integer, 
  //  locks_count: integer, 
  //  can_manage_users: boolean, 
  //  can_grant_edit_permission: boolean, 
  //  bio: string, 
  //  secondary_manager: boolean, 
  //  hire_date: date, 
  //  leave_date: date, 
  //  is_override_manager: boolean, 
  //  stand_in_manager_id: integer,
  //   on_break_manager_id: integer, 
  //   manager_on_break: boolean, 
  //   user_on_break_since: date, 
  //   hide_profile: boolean, 
  //   featured_item_id: integer, 
  //   user_on_break: boolean, 
  //   limit_messaging: boolean,
  //    unseen_announcements: boolean, 
  //    switch_source_company_id: integer,
  //     login_name: string, 
  //     has_unread_notifications: boolean, 
  //     onboarding_step: integer, 
  //     deactivation_reason: string, 
  //     custom_terms_accepted_at: datetime, 
  //     custom_privacy_policy_accepted_at: datetime, 
  //     will_delete_at: date)