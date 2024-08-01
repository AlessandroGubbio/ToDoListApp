package com.example.ToDoListAppSpring.repository;

import com.example.ToDoListAppSpring.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

//  from the table mapped by the entity so Task
    @Query("SELECT t FROM Task t where t.user_id =:user and t.completed = false order by t.dueDate")
    List<Task> findByUseridOrderByDueDate(long user);

    @Query("SELECT t FROM Task t where t.user_id =:user and t.completed = true order by t.dueDate")
    List<Task> findByUseridTrueOrderByDueDate(long user);

}

