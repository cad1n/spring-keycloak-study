package com.taskco.services;

import com.taskco.entity.User;
import com.taskco.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }

    public User save(User user) {
        return repository.save(user);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}