package com.taskco.controllers;

import com.taskco.dto.UserTeamDTO;
import com.taskco.entity.UserTeam;
import com.taskco.mapper.UserTeamMapper;
import com.taskco.services.UserTeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-teams")
public class UserTeamController {

    private final UserTeamService service;
    private final UserTeamMapper mapper;

    public UserTeamController(UserTeamService service, UserTeamMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserTeamDTO>> findAll() {
        return ResponseEntity.ok(mapper.toDTOList(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTeamDTO> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(mapper.toDTO(service.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserTeamDTO> create(@RequestBody UserTeamDTO userTeamDTO) {
        UserTeam userTeam = mapper.toEntity(userTeamDTO);
        return ResponseEntity.ok(mapper.toDTO(service.save(userTeam)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTeamDTO> update(@PathVariable Integer id, @RequestBody UserTeamDTO userTeamDTO) {
        try {
            service.findById(id);
            UserTeam userTeam = mapper.toEntity(userTeamDTO);
            userTeam.setId(id);
            return ResponseEntity.ok(mapper.toDTO(service.save(userTeam)));
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
