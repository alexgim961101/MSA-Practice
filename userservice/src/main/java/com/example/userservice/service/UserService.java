package com.example.userservice.service;

import com.example.userservice.domain.user.User;
import com.example.userservice.dto.RequestUser;
import com.example.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDto createUser(RequestUser dto);

    UserDto getUserByUserId(String userId);

    User getUserById(Long id);

    Iterable<User> getUserByAll();

    UserDto getUSerDetailsByEmail(String username);
}
