package com.hanghea.list99.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ErrorResult {

    private String status;
    private String message;
}
