package com.hanghea.list99.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResult {

//    private HttpStatus status;
    private String status;
    private String message;

}
