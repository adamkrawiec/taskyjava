package com.example.demo.Item.ItemTranslation;

import java.time.LocalDateTime;
import java.util.Date;

import com.example.demo.Item.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Entity
@Data
@NoArgsConstructor
@Embeddable
@Table(name = "item_translations")
@EntityListeners(AuditingEntityListener.class)
public class ItemTranslation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @Column(name = "title")
  @NotBlank(message = "Title cannot be blank")
  private String title;

  @Column
  private String description;

  @Column
  private String locale;

  @ManyToOne
  @JoinColumn(name = "item_id", referencedColumnName = "id")
  private Item item;

  @CreatedDate
  // @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  // @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
