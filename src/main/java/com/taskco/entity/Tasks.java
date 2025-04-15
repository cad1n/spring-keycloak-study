package com.taskco.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tasks {
    private Integer id;
    private String description;
    private String priority;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Integer categoryId;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Integer teamId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Integer createdBy;
}
