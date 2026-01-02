package com.taskco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTeamDTO {
    private Integer id;
    private Integer userId;
    private Integer teamId;
    private String role;
}
