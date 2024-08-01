package com.example.ToDoListAppSpring.controller;

import com.example.ToDoListAppSpring.dto.TaskDTO;
import com.example.ToDoListAppSpring.dto.UserDTO;
import com.example.ToDoListAppSpring.entity.User;
import com.example.ToDoListAppSpring.service.TaskService;
import com.example.ToDoListAppSpring.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;


    @GetMapping("home")
    public ModelAndView home(HttpSession session){
        if(session.getAttribute("username") == null){
            return new ModelAndView("login");
        }
        return new ModelAndView("home");
    }

    @GetMapping
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @PostMapping("login")
    public ModelAndView login(@ModelAttribute("user") User user, HttpSession session){
        if(userService.findByUsernameAndPassword(user) == null){
            logger.error("user not registered");
            return new ModelAndView("login", "error",
                    "There was an error with your credentials");
        }
        session.setAttribute("username", user.getUsername());
        return new ModelAndView("home");
    }


    @GetMapping("/register")
    public ModelAndView getRegister(){
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("user") User user, HttpSession session){
        if(!userService.register(user)){
            logger.error("the username \"{}\" is already registered", user.getUsername());
            return new ModelAndView("register", "error",
                    "This username is already taken");
        }
        logger.info("registration successful for {}", user.getUsername());
        session.setAttribute("username", user.getUsername());
        return new ModelAndView("home");
    }

    @GetMapping("create")
    public ModelAndView getTask(HttpSession session){
        if(session.getAttribute("username") == null){
            return new ModelAndView("login");
        }
        return new ModelAndView("create");
    }

    @PostMapping("create")
    public ModelAndView createTask(@ModelAttribute("task") TaskDTO task, HttpSession session){
        LocalDateTime date = LocalDateTime.now();
        if(task.getDueDate().isBefore(date.toLocalDate())){
            return new ModelAndView("create", "error",
                    "The due Date cannot have already happened!");
        }
        task.setCreatedDate(date);
        task.setUser_id(userService.findByUsername((String) session.getAttribute("username")).getId());
        taskService.save(task);
        return new ModelAndView("create", "success", "The Task was successfully created!");
    }

    @GetMapping("review")
    public ModelAndView review(HttpSession session){
        if(session.getAttribute("username") == null){
            return new ModelAndView("login");
        }
        List<TaskDTO> taskDTOList = taskService.findByUserid(userService.findByUsername((String)
                session.getAttribute("username")).getId());
        return new ModelAndView("review", "lista", taskDTOList);
    }

    @PostMapping("/complete")
    public String complete(@RequestParam("checked") List<String> checked){
        taskService.completeTask(checked);
        return "redirect:/review";
    }

    @GetMapping("/viewCompleted")
    public ModelAndView getCompleted(HttpSession session){
        if(session.getAttribute("username") == null){
            return new ModelAndView("login");
        }
        UserDTO user = userService.findByUsername((String) (session.getAttribute("username")));
        List<TaskDTO> taskDTOS = taskService.findByUseridCompleted(user.getId());
        return new ModelAndView("completed", "lista", taskDTOS);
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:/";
    }

}
