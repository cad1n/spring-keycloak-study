package com.taskco.mapper;

import com.taskco.dto.UserTeamDTO;
import com.taskco.entity.UserTeam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserTeamMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "team.id", target = "teamId")
    UserTeamDTO toDTO(UserTeam userTeam);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "teamId", target = "team.id")
    UserTeam toEntity(UserTeamDTO dto);

    List<UserTeamDTO> toDTOList(List<UserTeam> userTeams);
    List<UserTeam> toEntityList(List<UserTeamDTO> userTeamDTOs);
}
