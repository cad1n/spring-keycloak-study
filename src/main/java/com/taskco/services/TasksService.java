package com.taskco.services;

import com.taskco.entity.Tasks;
import com.taskco.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {
    private final TasksRepository repository;

    public TasksService(TasksRepository repository) {
        this.repository = repository;
    }

    public List<Tasks> findAll() {
        return repository.findAll();
    }

    public Tasks findById(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }
}