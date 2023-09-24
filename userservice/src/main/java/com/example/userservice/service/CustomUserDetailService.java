package com.example.userservice.service;

import com.example.userservice.domain.user.User;
import com.example.userservice.domain.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

// 순환 참조 오류가 생겨서 따로 빼둠
@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepo.findByEmail(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("유저를 찾지 못했습니다");
        });

        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getEncPwd(), true, true, true, true, new ArrayList<>());
    }
}
