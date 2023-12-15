# Toy Project : Blog

##  ✨ 프로젝트 소개
``RESTful``, ``JWT``와 ``OAuth2`` 적용 및 구현이 목적인 개인 프로젝트입니다.<br/>
나만의 블로그를 제작했습니다. <br/>
기간 : 2023.11.30 ~ 2023.12.06 약 일주일<br/>

## 시스템 구성도
![시스템구성도](https://github.com/amung9914/blog_side_project/assets/137124338/650be16d-556b-4e15-81f3-ca0cae79cd0d)


## 🛠 Languages and Tools:
- Java17
- Spring Boot v3.2.0
- SpringSecurity v6.2.0
- OAuth2 Client v6.2.0
- Spring Data JPA v3.2.0
- Thymeleaf v6.3.1
- lombok v1.18.28
- Junit v5.10.1
- testng 7.1.0
- jjwt v0.9.1
- jaxb-api v2.3.1
- apache Tomcat 10.1.16
- Bootstrap v4.6.1
- Toast Ui Editor v3.2.2

## 💡 ERD : 
![image](https://github.com/amung9914/blog_side_project/assets/137124338/1c6077b7-b824-40be-bb7a-1eb24b723fa0)

## 📌 구현 기능 : 

### OAuth2.0를 사용한 구글 로그인

![image](https://github.com/amung9914/blog_side_project/assets/137124338/e0615efe-43db-45b2-969f-3b6b9ffe523b)
Oauth2.0과 JWT 토큰 방식을 활용하여 소셜 로그인을 구현했습니다.<br/>

### Token Based Authentication 구현
JWT의 ``AccessToken``의 보안 취약점 보완을 위해 ``RefreshToken``을 구현했습니다.<br/>
로그인 시 localStorage에 AccessToken을 저장, 쿠키에 RefreshToken을 저장하여<br/>
AccessToken이 만료된 경우 refreshToken으로 다시 accessToken을 요청하여 로그인이 지속되도록 구현했습니다.<br/>
이를 통해 자동로그인 구현과 보안문제를 보완했습니다.<br/>

### 글 목록 조회 기능
![image](https://github.com/amung9914/blog_side_project/assets/137124338/6e8b232c-ba2c-496c-8207-0c136e23be08)
로그인 후 바로 블로그 글 목록 화면으로 이동합니다. 
글은 최신순으로 정렬됩니다.

### 글 작성 기능
![image](https://github.com/amung9914/blog_side_project/assets/137124338/bd22d783-dc23-480b-ba1b-330f69c9b9d6)
글 작성 화면은 오픈소스 라이브러리인 TOAST UI Editor를 활용하였습니다.

### 글 조회 기능
![image](https://github.com/amung9914/blog_side_project/assets/137124338/2880fdb7-7d2f-4876-951c-234806eebed3)

글작성자와 작성시간 및 내용이 표시됩니다.

### 글 수정 및 삭제 기능
SecurityContext의 Authentication를 활용하여  글 작성자와 같은 유저인지 Backend에서 확인 후 작업을 수행하도록 구현했습니다.

### 로그아웃 기능
로그아웃 시 다시 login화면으로 돌아갑니다.

## 🧱 트러블슈팅 : 
### Spring security 6 적용으로 시큐리티 설정의 변화
websecurityconfigureadapter, authorizeRequests의 Deprecated 뿐만 아니라 많은 부분의 변화가 있어서 기존 방식으로 개발하는 것이 불가능했습니다. <br/>
그래서 JWT와 Oauth2.0 학습과 함께 ``WebOAuthSecurityConfig`` 및 설정 파일을 구성하는 것이 가장 어려웠습니다.<br/>
