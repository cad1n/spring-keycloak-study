package com.taskco.services;

import com.taskco.entity.Task;
import com.taskco.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Optional<Task> findById(Integer id) {
        return repository.findById(id);
    }

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public void deleteTask(Integer id) {
        repository.deleteById(id);
    }
}