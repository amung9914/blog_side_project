package com.blog.service;

import com.blog.config.jwt.TokenProvider;
import com.blog.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenService refreshTokenService;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken){
        // 리프레시 토큰으로 유효성 검사 진행
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("이 토큰은 유효하지 않습니다.");
        }
         // 유효하면 이 토큰으로 사용자id 검색
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);
        // 새로운 액세스 토큰 생성
        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
