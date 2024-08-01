package com.example.ToDoListAppSpring.service;

import com.example.ToDoListAppSpring.dto.TaskDTO;
import com.example.ToDoListAppSpring.dto.UserDTO;
import com.example.ToDoListAppSpring.entity.User;

public interface UserService {
    UserDTO findByUsernameAndPassword(User user);
    UserDTO findByUsername(String username);
    boolean register(User user);

}
