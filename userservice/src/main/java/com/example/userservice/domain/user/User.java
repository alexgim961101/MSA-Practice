package com.example.userservice.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String encPwd;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    protected User(Long id, String email, String pwd, String name, String userId, String encPwd, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.userId = userId;
        this.encPwd = encPwd;
        this.createdAt = createdAt;
    }

    //== 생성 메서드 ==//
    public static User createUserEntity(String email, String pwd, String name, String userId, String encPwd){
        return User.builder()
                .email(email)
                .pwd(pwd)
                .name(name)
                .userId(userId)
                .encPwd(encPwd)
                .build();
    }
}
