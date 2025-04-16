package com.taskco.services;

import com.taskco.entity.UserTeam;
import com.taskco.repositories.UserTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTeamService {
    private final UserTeamRepository repository;

    public UserTeamService(UserTeamRepository repository) {
        this.repository = repository;
    }

    public List<UserTeam> findAll() {
        return repository.findAll();
    }

    public UserTeam findById(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }
}