package com.hanghea.list99.dto;

import com.hanghea.list99.domain.Plan;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class PlanDto {
    @Getter
    @Setter
    public static class Request{
        private String title;
        private String content;
        private int stars;
    }

    @Getter
    @Setter
    public static class Response {
        private Long planId;
        private String title;
        private String content;
        private int stars;
        private LocalDateTime createdAt;
        private Boolean status;

        public Response(Plan plan){
            this.planId = plan.getPlanId();
            this.title = plan.getTitle();
            this.content = plan.getContent();
            this.stars = plan.getStars();
            this.createdAt = plan.getCreatedAt();
            this.status = plan.getStatus();
        }
    }
}
