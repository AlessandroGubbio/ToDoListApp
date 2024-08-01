package com.example.ToDoListAppSpring.mapper;

import com.example.ToDoListAppSpring.dto.TaskDTO;
import com.example.ToDoListAppSpring.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskDTO taskDTO);
    TaskDTO toDTO(Task task);
}
