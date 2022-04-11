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

    //회원가입 1.
//    @PostMapping("/join")
//    public void userjoin (@RequestBody @Valid UserRequestDto requestDto){
//        userService.registerUser(requestDto);
//    }
    //회원가입 2.
    @PostMapping("/join")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestDto requestDto) {
        System.out.println(requestDto);
        userService.registerUser(requestDto);
        System.out.println("회원가입 완료");
        return ResponseEntity.ok()
                .body("회원가입 완료");
    }


//    사용자 정보 조회 1.
    //get 요청을 보내면 안됐다.
    @PostMapping("/isLogin")
    public ResponseEntity<String> getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(userDetails.getUsername());
        return ResponseEntity.ok().body(userDetails.getUsername());
    }
    //사용자 정보 조회 2.
//    @GetMapping("/isLogin")
//    public UserDto getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        User user = userDetails.getUser();
//        return userService.getUserInfo(user);
//    }


    //유저 탈퇴
//    @PostMapping("/remove")
//    public void userRemove(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        userService.remove(userDetails.getUser().getId());
//    }

    //유저 탈퇴 .2
    @PostMapping("/remove")
    public ResponseEntity<String> userRemove(@AuthenticationPrincipal UserDetailsImpl userDetails){
        userService.remove(userDetails.getUser().getId());
        return ResponseEntity.ok().body("회원 탈퇴가 완료되었습니다.");
    }
}




