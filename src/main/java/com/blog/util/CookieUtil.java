package com.blog.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

public class CookieUtil {

    /**
     *  쿠키 추가
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 쿠키 삭제(입력받은 이름의 쿠키를 삭제)
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return;
        }

        for(Cookie cookie : cookies){
            if(name.equals(cookie.getName())){
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    /**
     * 객체를 직렬화해서 쿠키의 값으로 변환함
     */
    public static String serialize(Object obj){
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }


    /**
     * 쿠키의 역직렬화(객체로 변환)
     */
    public static <T> T deserialize(Cookie cookie, Class<T> cls){
        byte[] data = Base64.getUrlDecoder().decode(cookie.getValue());
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return cls.cast(ois.readObject());
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
