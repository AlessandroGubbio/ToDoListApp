package com.example.ToDoListAppSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    private boolean completed;
    private LocalDate dueDate;
    private LocalDateTime createdDate;
    private LocalDateTime completedOn;
    @Column(name = "user_id")
    private long user_id;


}
