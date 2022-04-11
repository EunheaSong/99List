package com.hanghea.list99.security.provider;

import com.auth0.jwt.JWT;
import com.hanghea.list99.domain.User;
import com.hanghea.list99.repository.UserRepository;
import com.hanghea.list99.security.UserDetailsImpl;
import com.hanghea.list99.security.jwt.JwtDecoder;
import com.hanghea.list99.security.jwt.JwtPreProcessingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTAuthProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        String userId = jwtDecoder.decodeUsername(token); //복호화

        //ODO: API 사용시마다 매번 User DB 조회 필요
        //-> 해결을 위해서는 UserDetailsImpl 에 User 객체를 저장하지 않도록 수정
        //ex) UserDetailsImpl 에 userId, username, role 만 저장
//JWT 에 userId, username, role 정보를 암호화/복호화하여 사용

        User user = userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + userId));;
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
