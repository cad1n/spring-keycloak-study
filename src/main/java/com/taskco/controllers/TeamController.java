package com.taskco.controllers;

import com.taskco.dto.TeamDTO;
import com.taskco.entity.Team;
import com.taskco.mapper.TeamMapper;
import com.taskco.services.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService service;
    private final TeamMapper mapper;

    public TeamController(TeamService service, TeamMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> findAll() {
        return ResponseEntity.ok(mapper.toDTOList(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(mapper.toDTO(service.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TeamDTO> create(@RequestBody TeamDTO teamDTO) {
        Team team = mapper.toEntity(teamDTO);
        return ResponseEntity.ok(mapper.toDTO(service.save(team)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable Integer id, @RequestBody TeamDTO teamDTO) {
        try {
            service.findById(id);
            Team team = mapper.toEntity(teamDTO);
            team.setId(id);
            return ResponseEntity.ok(mapper.toDTO(service.save(team)));
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
