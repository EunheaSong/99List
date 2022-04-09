package com.hanghea.list99.controller;

import com.hanghea.list99.dto.UserRequestDto;
import com.hanghea.list99.exceptionHandler.ErrorResult;
import com.hanghea.list99.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping("/join")
    public void userjoin (@RequestBody @Validated UserRequestDto requestDto){
        userService.registerUser(requestDto);
//        return
    }

}




