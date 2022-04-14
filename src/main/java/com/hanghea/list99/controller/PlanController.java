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
import org.springframework.web.bind.annotation.*;

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

    //plan 등록 테스트
    @PostMapping("/api/plan")
    public ResponseEntity<String> createPlans(@RequestBody PlanDto.Request request, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        planService.createPlans(request, user);
        return ResponseEntity.ok().body("plan 등록 완료");
    }

    // plan 수정
    @PutMapping("/api/plan/{planId}")
    public ResponseEntity<String> updatePlan(@PathVariable Long planId, @RequestBody PlanDto.Request request) {
        planService.update(planId, request);
        return ResponseEntity.ok().body("plan 수정 완료!");
    }

    //plan 체크박스 클릭
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

    @DeleteMapping("/api/plan/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Long planId) {
        planRepository.deleteById(planId);
        return ResponseEntity.ok().body("게시물 삭제 완료!");
    }

    @GetMapping("/api/plan/{planId}/detail")
    public PlanDto.Response getPlanDetail(@PathVariable Long planId){
        return planService.getPlanDetail(planId);
    }


}
