package com.green.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.dto.UserDto;
import com.green.entity.User;
import com.green.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(UserDto dto) {
        return userRepository.save(
        	User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                // 패스워드는 WebSecurityConfig.java 내에 있는 bCryptPasswordEncoder 함수를 실행해서 저장
                .build()
            ).getId();
        // dto 안에 있는 data 를 저장하고
        // 저장된 id return
    }
}
