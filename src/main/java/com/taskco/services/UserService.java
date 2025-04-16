package com.taskco.services;

import com.taskco.entity.Users;
import com.taskco.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<Users> findAll() {
        return repository.findAll();
    }

    public Users findById(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }
}