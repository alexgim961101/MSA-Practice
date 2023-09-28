package com.example.userservice.service;

import com.example.userservice.client.OrderServiceClient;
import com.example.userservice.domain.user.User;
import com.example.userservice.domain.user.UserRepo;
import com.example.userservice.dto.RequestUser;
import com.example.userservice.dto.ResponseOrder;
import com.example.userservice.dto.UserDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    // private final RestTemplate restTemplate;
    private final OrderServiceClient orderServiceClient;

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

        // List<ResponseOrder> orders = new ArrayList<>();

//        // restemplate 사용
//        String orderUrl = String.format("http://ORDER-SERVICE/order-service/%s/orders", userId);
//        ResponseEntity<List<ResponseOrder>> orderListResponse = restTemplate.exchange(orderUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<ResponseOrder>>() {});
//        List<ResponseOrder> orders = orderListResponse.getBody();

//        List<ResponseOrder> orders = null;
//        try {
//            orderServiceClient.getOrders(userId);
//        } catch (FeignException ex) {
//            log.error(ex.getMessage());
//        }

        List<ResponseOrder> orders = orderServiceClient.getOrders(userId);
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
