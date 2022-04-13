package com.hanghea.list99.exceptionHandler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    //회원가입 유효성 검사에러
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ErrorResult illegalExHandle(IllegalArgumentException e){
//
//        return new ErrorResult("false", e.getMessage());
//    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity illegalExHandle(IllegalArgumentException e){
        log.error(e.getMessage());
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity illegalExHandle(BadCredentialsException e){
        log.error(e.getMessage());
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResult illegalStateHandle(IllegalStateException e){
        log.error(e.getMessage());
        return new ErrorResult("false", e.getMessage());
    }

/* 추가해볼 예외상황
* 로그인시, 아이디 비밀번호가 일치하지 않을 때
*
* */
  }
