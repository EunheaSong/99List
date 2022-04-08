package com.hanghea.list99.service;


import com.hanghea.list99.domain.Plan;
import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.PlanDto;
import com.hanghea.list99.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlanService {
    private final PlanRepository planRepository;

    public void createPlans(List<PlanDto.Request> requestList, User user) {
        for (PlanDto.Request request : requestList) {
            Plan plan = new Plan(request, user);
            validatePlan(plan);
            planRepository.save(plan);
        }
    }

    public List<PlanDto.Response> getPlans(User user) {
        List<Plan> planList = planRepository.findByUser(user);
        List<PlanDto.Response> responseList = new ArrayList<>();
        for(Plan plan : planList){
            PlanDto.Response response = new PlanDto.Response(plan);
            responseList.add(response);
        }

        return responseList;
    }

    public void validatePlan(Plan plan){
        String title = plan.getTitle();
        String content = plan.getContent();
        int stars = plan.getStars();


        if (title == null || title.equals("")) {
            throw new IllegalStateException("제목이 올바르지 않습니다.");
        }
//        if (title == null || title.equals("")) {
//            throw new IllegalArgumentException("음식 명이 올바르지 않습니다.");
//        }
        if (content == null || content.equals("")) {
            throw new IllegalStateException("내용이 올바르지 않습니다.");
        }

        if (stars < 0 || stars > 5){
            throw new IllegalStateException("중요도의 범위가 올바르지 않습니다.");
        }

    }
}
