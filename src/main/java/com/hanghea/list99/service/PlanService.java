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

    public Page<Plan> getPlans(Long userId, int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Plan> planList = planRepository.findAllByUserId(userId, pageable);
//        Page<PlanDto.Response> responseList = new ArrayList<>();
//        for (Plan plan : planList) {
//            PlanDto.Response response = new PlanDto.Response(plan);
//            responseList.add(response);
//        }

        return planList;
    }

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
}
