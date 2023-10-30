package com.aathif.its.task.controller;

import com.aathif.its.task.dto.TaskDTO;
import com.aathif.its.task.dto.UpdateTaskDTO;
import com.aathif.its.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody TaskDTO taskDTO) {
        return taskService.saveTask(taskDTO);
    }

    @GetMapping("/load")
    public ResponseEntity<?> getAllTask() {
        return taskService.loadTask();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UpdateTaskDTO updateTaskDTO) {
        return taskService.updateTask(id, updateTaskDTO);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return taskService.delete(id);
    }

}
