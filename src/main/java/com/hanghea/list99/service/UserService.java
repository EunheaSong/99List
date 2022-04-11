package com.hanghea.list99.service;

import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.UserDto;
import com.hanghea.list99.dto.UserRequestDto;
import com.hanghea.list99.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUserId();
        Optional<User> found = userRepository.findUserByUserId(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        isValidUser(requestDto);

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getUserPw());


        User user = new User(username, password);
        userRepository.save(user);
    }

    private void isValidUser(UserRequestDto requestDto) {
        String username = requestDto.getUserId();
        String password = requestDto.getUserPw();
        String confirmPassword = requestDto.getPwCheck();

        // 아이디 유효성 검사
        Pattern usernamePattern = Pattern.compile("^[가-힣a-zA-Z0-9-_.]{2,10}$");
        Matcher usernameMatcher = usernamePattern.matcher(username);
        if(username.length() == 0){
            throw new IllegalArgumentException("아이디는 필수 입력값입니다.");
        }
        if(!usernameMatcher.matches()) {
            throw new IllegalArgumentException("2자~10자 한글,영문,숫자 특수문자-_ 조합으로 입력해주세요.");
        }

        // 비밀번호 유효성 검사
        Pattern passwordPattern = Pattern.compile("^[\\S]*$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(password.length() == 0){
            throw new IllegalArgumentException("비밀번호는 필수 입력값입니다.");
        }
        if(!passwordMatcher.matches()) {
            throw new IllegalArgumentException("비밀번호는 영어,숫자,특수문자를 사용하여 8~16자로 입력해주세요.");
        }

        // password 일치여부
        if(confirmPassword.length() == 0){
            throw new IllegalArgumentException("비밀번호 재확인은 필수입니다.");
        }
        if(!password.equals(confirmPassword)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

//    private UserDto generateUserResponseDto(User user) {
//        return UserDto.builder()
//                .id(user.getId())
//                .userId(user.getUserId())
//                .plans(user.getPlans())
//                .build();
//    }

    //유저 프로필 조회
    @Transactional
    public UserDto getUserInfo(User user) {
        UserDto userDto = new UserDto(user);
        return userDto;
    }

}
