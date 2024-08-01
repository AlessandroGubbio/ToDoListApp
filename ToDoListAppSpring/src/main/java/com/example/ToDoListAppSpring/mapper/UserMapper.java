package com.example.ToDoListAppSpring.mapper;

import com.example.ToDoListAppSpring.dto.UserDTO;
import com.example.ToDoListAppSpring.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
}
