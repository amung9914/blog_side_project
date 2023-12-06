package com.blog.util;

import com.blog.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CookieUtilTest {
    
    @Test
    public void serialize() throws Exception {
        // given
        User testUser = User.builder()
                .email("user@gmail.com")
                .password("test")
                .build();

        // when
        String cookie = CookieUtil.serialize(testUser);
        System.out.println(cookie);

        // then

    }

}