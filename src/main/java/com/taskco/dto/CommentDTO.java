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
public class CommentDTO {
    private Integer id;

    @NotBlank(message = "Comment text is required")
    private String text;
    private LocalDateTime createdAt;

    @NotNull(message = "Task ID is required")
    private Integer taskId;

    @NotNull(message = "User ID is required")
    private Integer userId;
}
