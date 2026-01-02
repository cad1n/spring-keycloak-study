package com.taskco.mapper;

import com.taskco.dto.CommentDTO;
import com.taskco.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "user.id", target = "userId")
    CommentDTO toDTO(Comment comment);

    @Mapping(source = "taskId", target = "task.id")
    @Mapping(source = "userId", target = "user.id")
    Comment toEntity(CommentDTO dto);

    List<CommentDTO> toDTOList(List<Comment> comments);
    List<Comment> toEntityList(List<CommentDTO> commentDTOs);
}
