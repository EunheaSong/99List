package com.hanghea.list99.controller;

import com.hanghea.list99.domain.Plan;
import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.PlanDto;
import com.hanghea.list99.repository.PlanRepository;
import com.hanghea.list99.security.UserDetailsImpl;
import com.hanghea.list99.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
//@Controller
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final PlanRepository planRepository;

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

//    @PostMapping("/api/plan")
//    public void createPlans(PlanDto.Request request){
//        User user = new User();
//        planService.createPlans(request, user);
//    }

    //plan 등록 테스트
    @PostMapping("/api/plan")
    public void createPlans(@RequestBody PlanDto.Request request, @AuthenticationPrincipal UserDetailsImpl userDetails){

        User user = userDetails.getUser();
        planService.createPlans(request, user);
    }



    // plan 수정
    @PutMapping("/api/plan/{planId}")
    public Long updatePlan(@PathVariable Long planId, @RequestBody PlanDto.Request request) {
        planService.update(planId, request);
        return planId;
    }
    // 체크 박스 흠.. 잘모르겠음. 도움이 필요해요 ㅠㅠ
//    @PutMapping("/api/plan/{planId}/status")
//    public Long checkPlan(@PathVariable Long planId, @RequestBody PlanDto.Response response) {
//        planService.check(planId, response);
//        return planId;
//    }
    //포스트맨에서 테스트 시 ,
    @PatchMapping("/api/plan/{planId}/status")
    public Boolean checkPlan(@PathVariable Long planId) {
        Plan p = planService.check(planId);
        if (p.getStatus() == false){
            p.setStatus(true);
        } else {
            p.setStatus(false);
        }
        planRepository.save(p);
        return p.getStatus();
    }


    // plan 삭제
    @DeleteMapping("/api/plan/{planId}")
    public Long deletePlan(@PathVariable Long planId) {
        planRepository.deleteById(planId);
        return planId;
    }


}
