package com.example.ToDoListAppSpring.service;

import com.example.ToDoListAppSpring.dto.TaskDTO;
import com.example.ToDoListAppSpring.dto.UserDTO;
import com.example.ToDoListAppSpring.entity.User;
import com.example.ToDoListAppSpring.mapper.UserMapper;
import com.example.ToDoListAppSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDTO findByUsernameAndPassword(User user) {
        return userMapper.toDTO(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()));
    }

    @Override
    public UserDTO findByUsername(String username) {
        return mapToDTO(userRepository.findByUsername(username));
    }

    @Override
    public boolean register(User user) {
        if(userRepository.findByUsername(user.getUsername()) == null){
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private User mapToEntity(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .tasks(userDTO.getTasks())
                .build();
    }

    private UserDTO mapToDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .tasks(user.getTasks())
                .build();
    }


}
