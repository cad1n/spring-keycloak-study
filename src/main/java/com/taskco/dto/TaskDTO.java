package com.taskco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Integer id;
    private String description;
    private String priority;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private String status;
    private Integer categoryId;
    private Integer teamId;
    private Integer createdById;
}
