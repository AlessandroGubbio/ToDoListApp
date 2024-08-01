package com.example.ToDoListAppSpring.dto;

import com.example.ToDoListAppSpring.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private long id;
    private String username;
    private String password;
    private List<Task> tasks;
}
