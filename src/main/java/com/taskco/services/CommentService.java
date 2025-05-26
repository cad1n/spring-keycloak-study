package com.taskco.services;

import com.taskco.entity.Comment;
import com.taskco.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> findAll() {
        return repository.findAll();
    }

    public Comment findById(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }
}
