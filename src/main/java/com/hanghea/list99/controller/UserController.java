package com.hanghea.list99.controller;

import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.UserDto;
import com.hanghea.list99.dto.UserRequestDto;
import com.hanghea.list99.exceptionHandler.ErrorResult;
import com.hanghea.list99.security.UserDetailsImpl;
import com.hanghea.list99.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping("/join") //회원가입
    public void userjoin (@RequestBody @Valid UserRequestDto requestDto){
        userService.registerUser(requestDto);
    }

//    사용자 정보 조회 1.
    @GetMapping("/isLogin")
    public ResponseEntity<UserDto> getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(userService.getUserInfo(user));
    }
    //사용자 정보 조회 2.
//    @GetMapping("/isLogin")
//    public UserDto getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        User user = userDetails.getUser();
//        return userService.getUserInfo(user);
//    }
}




