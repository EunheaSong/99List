package com.hanghea.list99.controller;

import com.hanghea.list99.dto.UserDto;
import com.hanghea.list99.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/join")
    public void registerUser(@RequestBody UserDto userDto){
        userService.register(userDto);
    }
}
