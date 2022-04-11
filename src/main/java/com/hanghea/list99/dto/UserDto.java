package com.hanghea.list99.dto;

import com.hanghea.list99.domain.Plan;
import com.hanghea.list99.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;

    private String userId;

    private List<PlanDto.Response> plans;

    public UserDto (User user){
        this.setId(user.getId());
        this.setUserId(user.getUserId());
//        for(Plan p : user.getPlans()){
//            PlanDto.Response planDto = new PlanDto.Response(p);
//            this.getPlans().add(planDto);
//        }
    }

}
