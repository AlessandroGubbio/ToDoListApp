package com.example.ToDoListAppSpring.service;

import com.example.ToDoListAppSpring.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    void save(TaskDTO task);

    List<TaskDTO> findByUserid(long user_id);

    TaskDTO findById(long l);

    List<TaskDTO> findByUseridCompleted(long user_id);
    void completeTask(List<String> checked);
}
