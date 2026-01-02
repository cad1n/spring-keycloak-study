package com.taskco.services;

import com.taskco.entity.Task;
import com.taskco.repositories.TaskRepository;
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
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void findAll_returnsList() {
        Task task = new Task();
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<Task> result = taskService.findAll();

        assertEquals(1, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void findById_found_returnsTask() {
        Task task = new Task();
        task.setId(1);
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    void findById_notFound_returnsEmpty() {
        when(taskRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Task> result = taskService.findById(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void saveTask_returnsSavedTask() {
        Task task = new Task();
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.saveTask(task);

        assertNotNull(result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void deleteTask_callsRepository() {
        doNothing().when(taskRepository).deleteById(1);

        taskService.deleteTask(1);

        verify(taskRepository, times(1)).deleteById(1);
    }
}
