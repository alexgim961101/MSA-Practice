package com.example.userservice.dto;

import com.example.userservice.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)  // null이 아닌 값만 json으로 보여줌
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    private List<ResponseOrder> orders;

    @Builder
    public ResponseUser(String email, String name, String userId, List<ResponseOrder> orders) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.orders = orders;
    }

    public static ResponseUser createResponseUser(UserEntity entity) {
        return ResponseUser.builder()
                .userId(entity.getUserId())
                .name(entity.getName())
                .email(entity.getEmail())
                .orders(null)
                .build();
    }

    public static ResponseUser createResponseUser(UserDto dto) {
        return ResponseUser.builder()
                .userId(dto.getUserId())
                .name(dto.getName())
                .email(dto.getEmail())
                .orders(dto.getOrders())
                .build();
    }
}
