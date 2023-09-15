package com.example.userservice.dto;

import com.example.userservice.domain.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String userId;
    private LocalDateTime createdAt;

    private String encPwd;

    private List<ResponseOrder> orders;


    @Builder
    public UserDto(String email, String pwd, String name, String userId, LocalDateTime createdAt, String encPwd, List<ResponseOrder> orders) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.userId = userId;
        this.createdAt = createdAt;
        this.encPwd = encPwd;
        this.orders = orders;
    }

    //== 생성 메서드 ==//
    public static UserDto fromEntity(UserEntity entity, List<ResponseOrder> orders) {
        return UserDto.builder()
                .email(entity.getEmail())
                .pwd(entity.getPwd())
                .name(entity.getName())
                .userId(entity.getUserId())
                .createdAt(entity.getCreatedAt())
                .encPwd(entity.getEncPwd())
                .orders(orders)
                .build();
    }
}
