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
}
