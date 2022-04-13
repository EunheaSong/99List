package com.hanghea.list99.controller;

import com.hanghea.list99.domain.Plan;
import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.PlanDto;
import com.hanghea.list99.repository.PlanRepository;
import com.hanghea.list99.security.UserDetailsImpl;
import com.hanghea.list99.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    ) {
        Long userId = userDetails.getUser().getId();
        page = page - 1;

        Page<Plan> target = planService.getPlans(userDetails.getUser(), page, size, sortBy, isAsc);

        return target;
    }
//    public List<Plan> getPlans(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        User user = userDetails.getUser();
//        return planService.getPlans(user);
//    }

    //plan 등록 테스트
    @PostMapping("/api/plan")
    public ResponseEntity<String> createPlans(@RequestBody PlanDto.Request request, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        planService.createPlans(request, user);
        return ResponseEntity.ok().body("plan 등록 완료");
    }

    // plan 수정
    @PutMapping("/api/plan/{planId}")
//    public Long updatePlan(@PathVariable Long planId, @RequestBody PlanDto.Request request) {
//        planService.update(planId, request);
//        return planId;
//    }
    public ResponseEntity<String> updatePlan(@PathVariable Long planId, @RequestBody PlanDto.Request request) {
        planService.update(planId, request);
        return ResponseEntity.ok().body("plan 수정 완료!");
    }

    // 체크 박스 흠.. 잘모르겠음. 도움이 필요해요 ㅠㅠ
//    @PutMapping("/api/plan/{planId}/status")
//    public Long checkPlan(@PathVariable Long planId, @RequestBody PlanDto.Response response) {
//        planService.check(planId, response);
//        return planId;
//    }
    //포스트맨에서 테스트 시 ,
    @PatchMapping("/api/plan/{planId}/status")
    public ResponseEntity<String> checkPlan(@PathVariable Long planId) {
        Plan p = planService.check(planId);
        if (p.getStatus() == false) {
            p.setStatus(true);
        } else {
            p.setStatus(false);
        }
        planRepository.save(p);
        return ResponseEntity.ok().body("plan 상태 변경 완료!");
    }
//    public Boolean checkPlan(@PathVariable Long planId) {
//        Plan p = planService.check(planId);
//        if (p.getStatus() == false) {
//            p.setStatus(true);
//        } else {
//            p.setStatus(false);
//        }
//        planRepository.save(p);
//        return p.getStatus();
//    }


    // plan 삭제
//    @DeleteMapping("/api/plan/{planId}")
//    public Long deletePlan(@PathVariable Long planId) {
//        planRepository.deleteById(planId);
//        return planId;
//    }


    @DeleteMapping("/api/plan/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Long planId) {
        planRepository.deleteById(planId);
        return ResponseEntity.ok().body("게시물 삭제 완료!");
    }


}
