package com.taskco.controllers;

import com.taskco.dto.CommentDTO;
import com.taskco.entity.Comment;
import com.taskco.mapper.CommentMapper;
import com.taskco.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService service;
    private final CommentMapper mapper;

    public CommentController(CommentService service, CommentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAll() {
        return ResponseEntity.ok(mapper.toDTOList(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(mapper.toDTO(service.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CommentDTO> create(@RequestBody @Valid CommentDTO commentDTO) {
        Comment comment = mapper.toEntity(commentDTO);
        return ResponseEntity.ok(mapper.toDTO(service.save(comment)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable Integer id, @RequestBody @Valid CommentDTO commentDTO) {
        try {
            service.findById(id);
            Comment comment = mapper.toEntity(commentDTO);
            comment.setId(id);
            return ResponseEntity.ok(mapper.toDTO(service.save(comment)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            service.findById(id);
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}