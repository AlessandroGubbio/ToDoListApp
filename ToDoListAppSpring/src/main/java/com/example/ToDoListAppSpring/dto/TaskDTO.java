package com.example.ToDoListAppSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private long id;
    private String title;
    private boolean completed;
    private LocalDate dueDate;
    private LocalDateTime createdDate;
    private LocalDateTime completedOn;

    private long user_id;
}
