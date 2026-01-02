package com.taskco.services;

import com.taskco.entity.Team;
import com.taskco.repositories.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @Test
    void findAll_returnsList() {
        Team team = new Team();
        when(teamRepository.findAll()).thenReturn(List.of(team));

        List<Team> result = teamService.findAll();

        assertEquals(1, result.size());
        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void findById_found_returnsTeam() {
        Team team = new Team();
        team.setId(1);
        when(teamRepository.findById(1)).thenReturn(Optional.of(team));

        Team result = teamService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void findById_notFound_throwsException() {
        when(teamRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> teamService.findById(1));
    }

    @Test
    void save_returnsSavedTeam() {
        Team team = new Team();
        when(teamRepository.save(team)).thenReturn(team);

        Team result = teamService.save(team);

        assertNotNull(result);
        verify(teamRepository, times(1)).save(team);
    }

    @Test
    void delete_callsRepository() {
        doNothing().when(teamRepository).deleteById(1);

        teamService.delete(1);

        verify(teamRepository, times(1)).deleteById(1);
    }
}
