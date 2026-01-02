package com.taskco.mapper;

import com.taskco.dto.TaskDTO;
import com.taskco.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "team.id", target = "teamId")
    @Mapping(source = "createdBy.id", target = "createdById")
    TaskDTO toDTO(Task task);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "teamId", target = "team.id")
    @Mapping(source = "createdById", target = "createdBy.id")
    Task toEntity(TaskDTO dto);

    List<TaskDTO> toDTOList(List<Task> tasks);
    List<Task> toEntityList(List<TaskDTO> taskDTOs);
}
