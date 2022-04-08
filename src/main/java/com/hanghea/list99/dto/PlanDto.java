package com.hanghea.list99.dto;

import lombok.Getter;
import lombok.Setter;

public class PlanDto {
    @Getter
    @Setter
    public static class Request{
        private String title;
        private String content;
        private int stars;
    }

}
