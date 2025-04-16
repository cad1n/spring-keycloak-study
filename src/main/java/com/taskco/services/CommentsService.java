package com.taskco.services;

import com.taskco.entity.Comments;
import com.taskco.repositories.CommentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {
    private final CommentsRepository repository;

    public CommentsService(CommentsRepository repository) {
        this.repository = repository;
    }

    public List<Comments> findAll() {
        return repository.findAll();
    }

    public Comments findById(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }
}
