package com.aathif.its.task.service;

import com.aathif.its.task.dto.TaskDTO;
import com.aathif.its.task.dto.UpdateTaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    ResponseEntity<?> saveTask(TaskDTO taskDTO);

    ResponseEntity<?> loadTask();

    ResponseEntity<?> updateTask(Long id, UpdateTaskDTO updateTaskDTO);

    ResponseEntity<?> delete(Long id);
}
