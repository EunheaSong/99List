package com.hanghea.list99.controller;

import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.PlanDto;
import com.hanghea.list99.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping("/api/plan")
    public void getPlans(List<PlanDto.Request> requestList){
        User user = new User();
        planService.createPlans(requestList, user);
    }
}
