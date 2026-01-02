package com.taskco.controllers;

import com.taskco.dto.TaskDTO;
import com.taskco.entity.Task;
import com.taskco.mapper.TaskMapper;
import com.taskco.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper mapper;

    @Autowired
    public TaskController(TaskService taskService, TaskMapper mapper) {
        this.taskService = taskService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findAllTasks() {
        return ResponseEntity.ok(mapper.toDTOList(taskService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> findTaskById(@PathVariable Integer id) {
        return taskService.findById(id)
            .map(mapper::toDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = mapper.toEntity(taskDTO);
        Task savedTask = taskService.saveTask(task);
        return ResponseEntity.ok(mapper.toDTO(savedTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) {
        return taskService.findById(id)
            .map(existingTask -> {
                Task task = mapper.toEntity(taskDTO);
                task.setId(id);
                return ResponseEntity.ok(mapper.toDTO(taskService.saveTask(task)));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        if (taskService.findById(id).isPresent()) {
            taskService.deleteTask(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}