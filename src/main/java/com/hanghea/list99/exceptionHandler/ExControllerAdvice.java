package com.hanghea.list99.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExControllerAdvice {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResult> processValidationError(MethodArgumentNotValidException e) {
//        BindingResult bindingResult = e.getBindingResult();
//
//        StringBuilder builder = new StringBuilder();
//        for (FieldError fieldError : bindingResult.getFieldErrors()) {
//            builder.append(fieldError.getDefaultMessage());
//        }
//        System.out.println(builder.toString());
//        return ResponseEntity.badRequest()
//                .body(new ErrorResult(HttpStatus.BAD_REQUEST, builder.toString()));
//    }
    //회원가입 유효성 검사에러
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e){

        return new ErrorResult("false", e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResult illegalStateHandle(IllegalStateException e){
        return new ErrorResult("false", e.getMessage());
    }

/* 추가해볼 예외상황
* 로그인시, 아이디 비밀번호가 일치하지 않을 때
*
* */
  }
