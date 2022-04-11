package com.hanghea.list99.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserRequestDto { //회원가입 요청 정보를 받아줄 녀석

//    @NotBlank(message = "아이디는 필수 입력값입니다.")
//    @Pattern(regexp = "^[가-힣a-zA-Z0-9-_.]{2,10}$", message = "2자~10자 한글,영문,숫자 특수문자-_ 조합으로 입력해주세요.")
    private String userId;

//    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z!@#$%^&*]{6,20}$", message = "6자~20자 영문,숫자 필수입력. 특수문자!@#$%^&* 조합으로 입력해주세요.")
    private String userPw;

//    @NotBlank(message = "비밀번호 재확인을 해주세요.")
    private String pwCheck;


}
