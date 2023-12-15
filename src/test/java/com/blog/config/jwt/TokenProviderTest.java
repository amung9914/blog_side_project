
package com.blog.config.jwt;

import com.blog.domain.User;
import com.blog.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TokenProviderTest {
    @Autowired private TokenProvider tokenProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired JwtProperties jwtProperties;

    @DisplayName("토큰생성 메서드")
    @Test
    public void testMethodNameHere() throws Exception {
        // given
        User testUser = userRepository.save(User.builder()
                .email("user@gmail.com")
                .password("test")
                .build());
        // when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        Long id = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);
        assertThat((id)).isEqualTo(testUser.getId());
    }


    @DisplayName("만료된 토큰일 때 유효성 검증 실패함")
    @Test
    public void validToken_invalid() throws Exception {
        // given
        String token = JwtFactory.builder().expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);
        // when
        boolean b = tokenProvider.validToken(token);

        // then
        assertThat(b).isFalse();
    }

    @DisplayName("유효한 토큰 검증")
    @Test
    public void validToken() throws Exception {
        // given
        String token = JwtFactory.withDefaultValues().createToken(jwtProperties);
        // when
        boolean result = tokenProvider.validToken(token);

        // then
        Assertions.assertThat(result).isEqualTo(true);
    }
    @DisplayName("토큰 기반으로 인증정보 가져오기")
    @Test
    public void getAuthentication() throws Exception {
        // given
        String userEmail = "user@gmail.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build().createToken(jwtProperties);

        // when
        Authentication authentication = tokenProvider.getAuthentication(token);
        // then
        Assertions.assertThat(((UserDetails)authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);
    }
    @DisplayName("토큰 기반으로 유저 ID 가져오기")
    @Test
    public void getUserId() throws Exception {
        // given
        Long userId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(jwtProperties);

        // when
        Long findId = tokenProvider.getUserId(token);
        // then
        Assertions.assertThat(findId).isEqualTo(userId);
    }

}
