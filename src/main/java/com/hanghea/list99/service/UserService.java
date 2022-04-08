package com.hanghea.list99.service;

import com.hanghea.list99.domain.User;
import com.hanghea.list99.dto.UserDto;
import com.hanghea.list99.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void register(UserDto userDto) {
        User user = new User(userDto);
        userRepository.save(user);
    }
}
