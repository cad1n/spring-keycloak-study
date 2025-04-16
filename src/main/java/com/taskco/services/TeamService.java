package com.taskco.services;

import com.taskco.entity.Teams;
import com.taskco.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Teams> findAll() {
        return repository.findAll();
    }

    public Teams findById(Integer id) {
        return this.repository.findById(id).orElseThrow();
    }
}