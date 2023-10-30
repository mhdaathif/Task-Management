package com.aathif.its.task.service.serviceImpl;

import com.aathif.its.task.dto.RequestMetaDTO;
import com.aathif.its.task.dto.TaskDTO;
import com.aathif.its.task.dto.UpdateTaskDTO;
import com.aathif.its.task.model.Task;
import com.aathif.its.task.model.User;
import com.aathif.its.task.repository.TaskRepository;
import com.aathif.its.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    RequestMetaDTO requestMetaDTO;

    @Override
    public ResponseEntity<?> loadTask() {
        List<Task> taskList = taskRepository.findAllByActive(true);
        return ResponseEntity.status(HttpStatus.OK).body(taskList);
    }

    @Override
    public ResponseEntity<?> saveTask(TaskDTO taskDTO) {
        if (taskDTO.getTitle().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Title Not Found");
        } else {
            Task task = new Task();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setDate(taskDTO.getDate());
            task.setUserId(requestMetaDTO.getId());
            taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task Register Successfully");
        }
    }

    @Override
    public ResponseEntity<?> updateTask(Long id, UpdateTaskDTO updateTaskDTO) {
        if (updateTaskDTO.getTitle().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Title Not Found");
        } else {
            Optional<Task> optionalTask = taskRepository.findById(id);
            if (optionalTask.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Task");
            }
            Task task = optionalTask.get();
            task.setTitle(updateTaskDTO.getTitle());
            task.setDescription(updateTaskDTO.getDescription());
            task.setDate(updateTaskDTO.getDate());
            task.setStatus(updateTaskDTO.getStatus());
            taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task Update Successfully");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Task");
        }
        Task task = optionalTask.get();
        task.setActive(false);
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body("Task Delete Successfully");
    }
}
