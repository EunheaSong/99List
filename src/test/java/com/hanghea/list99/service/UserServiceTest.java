package com.hanghea.list99.service;

import com.hanghea.list99.domain.User;
import com.hanghea.list99.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void 등록() {
        //given
        User user = new User("유저아이디", "비밀번호");

        //when
        userRepository.save(user);

        //then

        User savedUser = userRepository.findAll().get(0);

        assertThat(user.getUserId()).isEqualTo(savedUser.getUserId());
        assertThat(savedUser.getUserId()).isEqualTo("유저아이디");

    }
}