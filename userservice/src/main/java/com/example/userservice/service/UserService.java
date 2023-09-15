package com.example.userservice.service;

import com.example.userservice.domain.user.User;
import com.example.userservice.dto.RequestUser;
import com.example.userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(RequestUser dto);

    UserDto getUserByUserId(String userId);

    Iterable<User> getUserByAll();
}
