package com.taskco.controllers;

import com.taskco.entity.Comment;
import com.taskco.services.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Comment> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Comment findById(@PathVariable Integer id) {
        return service.findById(id);
    }
}