package com.example.userservice.service;

import com.example.userservice.domain.user.User;
import com.example.userservice.domain.user.UserRepo;
import com.example.userservice.dto.RequestUser;
import com.example.userservice.dto.ResponseOrder;
import com.example.userservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(RequestUser dto) {
        User user = User.createUserEntity(dto.getEmail(), dto.getPwd(), dto.getName(), UUID.randomUUID().toString(), passwordEncoder.encode(dto.getPwd()));
        User userEntity = userRepo.save(user);

        return UserDto.fromEntity(userEntity, null);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        User entity = userRepo.findByUserId(userId);

        if(entity == null) throw new UsernameNotFoundException("User Not Found");

        List<ResponseOrder> orders = new ArrayList<>();

        UserDto userDto = UserDto.fromEntity(entity, orders);

        return userDto;
    }

    @Override
    public User getUserById(Long id) {

        User userEntity = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Unexpected user"));

        return userEntity;
    }

    @Override
    public Iterable<User> getUserByAll() {
        return userRepo.findAll();
    }

    @Override
    public UserDto getUSerDetailsByEmail(String username) {
        User userEntity = userRepo.findByEmail(username).orElseThrow(() -> new IllegalArgumentException("Unexpected user"));

        return UserDto.fromEntity(userEntity, null);
    }
}
