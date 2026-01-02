package com.taskco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Description is required")
    private String description;
    
    @NotBlank(message = "Priority is required")
    private String priority;
    
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    
    @NotBlank(message = "Status is required")
    private String status;
    
    @NotNull(message = "Category ID is required")
    private Integer categoryId;
    
    private Integer teamId;
    
    @NotNull(message = "Creator ID is required")
    private Integer createdById;
}
