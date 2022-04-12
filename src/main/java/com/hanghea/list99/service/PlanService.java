package com.hanghea.list99.service;


import com.hanghea.list99.domain.Plan;
import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.PlanDto;
import com.hanghea.list99.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlanService {
    private final PlanRepository planRepository;

    public void createPlans(PlanDto.Request request, User user) {

        Plan plan = new Plan(request, user);
        validatePlan(plan);
        planRepository.save(plan);

    }

    public Page<Plan> getPlans(User user, int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Plan> planList = planRepository.findAllByUser(user, pageable);
//        Page<PlanDto.Response> responseList = new ArrayList<>();
//        for (Plan plan : planList) {
//            PlanDto.Response response = new PlanDto.Response(plan);
//            responseList.add(response);
//        }
        for(Plan plan : planList){
            plan.setUser(null);
        }
        return planList;
    }

//    public List<Plan> getPlans(User user){
//        Long userId= user.getId();
//        return planRepository.findAllByUserId(userId);
//    }

    public void validatePlan(Plan plan) {
        String title = plan.getTitle();
        String content = plan.getContent();
        int stars = plan.getStars();

        if (title == null || title.equals("")) {
            throw new IllegalStateException("제목이 올바르지 않습니다.");
        }

        if (content == null || content.equals("")) {
            throw new IllegalStateException("내용이 올바르지 않습니다.");
        }

        if (stars < 0 || stars > 5) {
            throw new IllegalStateException("중요도의 범위가 올바르지 않습니다.");
        }

    }
    // plan 수정
    @Transactional
    public void update(Long planId, PlanDto.Request request) {
        Plan plan = planRepository.findById(planId).orElseThrow(
                () -> new IllegalArgumentException("PlanId가 존재하지 않습니다.")
        );
        plan.update(request);
    }
    // 체크 박스
//    @Transactional
//    public void check(Long planId, PlanDto.Response response) {
//        Plan planCheck = planRepository.findById(planId).orElseThrow(
//                () -> new IllegalArgumentException("PlanId가 존재하지 않습니다.")
//        );
//        planCheck.check(response);
//    }

    @Transactional
    public Plan check(Long planId) {
        return planRepository.findById(planId).orElseThrow(
                () -> new IllegalArgumentException("PlanId가 존재하지 않습니다.")
        );
    }

}
