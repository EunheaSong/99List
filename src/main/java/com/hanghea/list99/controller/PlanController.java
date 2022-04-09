package com.hanghea.list99.controller;

import com.hanghea.list99.domain.Plan;
import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.PlanDto;
import com.hanghea.list99.security.UserDetailsImpl;
import com.hanghea.list99.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping("/api/plan")
    public Page<Plan> getPlans(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        Long userId = userDetails.getUser().getId();
        page = page - 1 ;
        return planService.getPlans(userId, page, size, sortBy, isAsc);
    }
//    public List<PlanDto.Response> getPlans(){
//        User user = new User();
//        return planService.getPlans(user);
//    }

    @PostMapping("/api/plan")
    public void createPlans(PlanDto.Request request){
        User user = new User();
        planService.createPlans(request, user);
    }
}
