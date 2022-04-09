package com.hanghea.list99.service;

import com.hanghea.list99.domain.Plan;
import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.PlanDto;
import com.hanghea.list99.repository.PlanRepository;
import com.hanghea.list99.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlanServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PlanService planService;
    @Autowired
    PlanRepository planRepository;


    @BeforeEach
    void 유저등록() {
        User user1 = new User();
        user1.setUserId("유저아이디1");
        user1.setUserPw("유저비밀번호");

        User user2 = new User();
        user2.setUserId("유저아이디2");
        user2.setUserPw("유저비밀번호");

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void createPlans() {
        //given
        PlanDto.Request request1 = new PlanDto.Request();
        request1.setTitle("타이틀1");
        request1.setContent("내용1");
        request1.setStars(4);

        PlanDto.Request request2 = new PlanDto.Request();
        request2.setTitle("타이틀2");
        request2.setContent("내용2");
        request2.setStars(1);


        User savedUser = userRepository.findAll().get(0);

        //when
        planService.createPlans(request1, savedUser);

        planService.createPlans(request2, savedUser);

        //given
        assertThat(planRepository.findAll().size()).isEqualTo(2);

        Plan plan1 = planRepository.findAll().get(0);

        assertThat(plan1.getTitle()).isEqualTo(request1.getTitle());
        assertThat(plan1.getContent()).isEqualTo(request1.getContent());
        assertThat(plan1.getContent()).isEqualTo("내용1");
        assertThat(plan1.getUser()).isEqualTo(savedUser);
    }

    @Test
    void getPlans() {

        //given
        PlanDto.Request request1 = new PlanDto.Request();
        request1.setTitle("타이틀1");
        request1.setContent("내용1");
        request1.setStars(4);

        PlanDto.Request request2 = new PlanDto.Request();
        request2.setTitle("타이틀2");
        request2.setContent("내용2");
        request2.setStars(1);


        User savedUser1 = userRepository.findAll().get(0);
        planService.createPlans(request1, savedUser1);
        planService.createPlans(request2, savedUser1);

        PlanDto.Request request3 = new PlanDto.Request();
        request3.setTitle("타이틀3");
        request3.setContent("내용3");
        request3.setStars(1);

        User savedUser2 = userRepository.findAll().get(1);
        planService.createPlans(request3, savedUser2);

        //when
        List<PlanDto.Response> responseList1 = planService.getPlans(savedUser1);
        List<PlanDto.Response> responseList2 = planService.getPlans(savedUser2);

        //then
        assertThat(responseList1.size()).isEqualTo(2);
        assertThat(responseList2.size()).isEqualTo(1);
        assertThat(responseList1.get(0).getTitle()).isEqualTo("타이틀1");

    }

    @Test
    void 제목_null() {
        //given
        PlanDto.Request request1 = new PlanDto.Request();
        request1.setTitle(null);
        request1.setContent("내용1");
        request1.setStars(4);

        User savedUser1 = userRepository.findAll().get(0);

        //when
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> planService.createPlans(request1, savedUser1)
        );

        //then
        assertThat(e.getMessage()).isEqualTo("제목이 올바르지 않습니다.");
    }
    @Test
    void 제목빈값() {
        //given
        PlanDto.Request request1 = new PlanDto.Request();
        request1.setTitle("");
        request1.setContent("내용1");
        request1.setStars(4);

        User savedUser1 = userRepository.findAll().get(0);

        //when
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> planService.createPlans(request1, savedUser1)
        );

        //then
        assertThat(e.getMessage()).isEqualTo("제목이 올바르지 않습니다.");
    }
    @Test
    void 내용_null() {
        //given
        PlanDto.Request request1 = new PlanDto.Request();
        request1.setTitle("제목");
        request1.setContent(null);
        request1.setStars(4);

        User savedUser1 = userRepository.findAll().get(0);

        //when
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> planService.createPlans(request1, savedUser1)
        );

        //then
        assertThat(e.getMessage()).isEqualTo("내용이 올바르지 않습니다.");
    }
    @Test
    void 내용빈값() {
        //given
        PlanDto.Request request1 = new PlanDto.Request();
        request1.setTitle("제목");
        request1.setContent("");
        request1.setStars(4);

        User savedUser1 = userRepository.findAll().get(0);

        //when
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> planService.createPlans(request1, savedUser1)
        );

        //then
        assertThat(e.getMessage()).isEqualTo("내용이 올바르지 않습니다.");
    }
    @Test
    void starsCheck1() {
        //given
        PlanDto.Request request1 = new PlanDto.Request();
        request1.setTitle("제목1");
        request1.setContent("내용1");
        request1.setStars(-1);

        User savedUser1 = userRepository.findAll().get(0);

        //when
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> planService.createPlans(request1, savedUser1)
        );

        //then
        assertThat(e.getMessage()).isEqualTo("중요도의 범위가 올바르지 않습니다.");
    }

    @Test
    void starsCheck2() {
        //given
        PlanDto.Request request1 = new PlanDto.Request();
        request1.setTitle("제목1");
        request1.setContent("내용1");
        request1.setStars(6);

        User savedUser1 = userRepository.findAll().get(0);

        //when
        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                () -> planService.createPlans(request1, savedUser1)
        );

        //then
        assertThat(e.getMessage()).isEqualTo("중요도의 범위가 올바르지 않습니다.");
    }
}