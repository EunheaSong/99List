package com.hanghea.list99.dto;

import com.hanghea.list99.domain.Plan;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String userId;

    private String userPw;

    private String pwCheck;

    private List<PlanDto> plans;

}
