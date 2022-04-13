package com.hanghea.list99.controller;

import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.UserDto;
import com.hanghea.list99.dto.UserRequestDto;
import com.hanghea.list99.exceptionHandler.ErrorResult;
import com.hanghea.list99.security.UserDetailsImpl;
import com.hanghea.list99.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
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
        userService.registerUser(requestDto);
        log.info("회원가입 완료");
        return ResponseEntity.ok()
                .body("회원가입 완료");
    }

    //아이디 중복 조회
//    @PostMapping("/")
//    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestDto requestDto) {
//
//        log.info("");
//        return ResponseEntity.ok()
//                .body("");
//    }


//    사용자 정보 조회 1.
    //get 요청을 보내면 안됐다.
    @PostMapping("/isLogin")
    public ResponseEntity<String> getUserProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        log.info(userDetails.getUsername());
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
//    @PostMapping("/remove")
//    public ResponseEntity userRemove(@RequestBody UserRequestDto requestDto){
//        try {
//            userService.remove(requestDto);
//            return new ResponseEntity ("회원 탈퇴가 완료되었습니다." , HttpStatus.OK);
//        }catch (IllegalArgumentException e){
//            return new ResponseEntity (e.getMessage(), HttpStatus.NOT_FOUND);
//        }catch (BadCredentialsException e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//    }
    @PostMapping("/remove")
    public ResponseEntity<String> userRemove(@RequestBody UserRequestDto requestDto) {
        userService.remove(requestDto);
        return ResponseEntity.ok().body("회원 탈퇴가 완료되었습니다.");
    }
}




