package com.taskco.mapper;

import com.taskco.dto.TeamDTO;
import com.taskco.entity.Team;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamDTO toDTO(Team team);
    Team toEntity(TeamDTO dto);
    List<TeamDTO> toDTOList(List<Team> teams);
    List<Team> toEntityList(List<TeamDTO> teamDTOs);
}
