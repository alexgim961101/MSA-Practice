package com.example.userservice.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

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

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    // 패스워드 반환
    @Override
    public String getPassword() {
        return this.encPwd;
    }

    // 로그인시 사용하는 id값 반환
    @Override
    public String getUsername() {
        return this.email;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}
