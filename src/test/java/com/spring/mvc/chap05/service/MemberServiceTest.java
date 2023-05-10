package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberServiceTest {
        @Autowired
        MemberService memberService;


        @Test
        @DisplayName("signUpDTO를 전달하면 회원가입에 성공해야 한다")
        void joinTest() {
            SignUpRequestDTO dto = new SignUpRequestDTO();
            dto.setAccount("kykqwewq");
            dto.setPassword("asdf");
            dto.setName("라라");
            dto.setEmail("aaa@aaa.com");

            memberService.join(dto);
        }

        @Test
    @DisplayName("계정명이 aaa1111 인 회원의 로그인시도시 결과 검증을 상황별로 수행해야한다")
    void loginTest(){
            //given
            LoginRequestDTO dto = new LoginRequestDTO();
            dto.setAccount("aaa1111");
            dto.setPassword("aaaaaa!");

            //when
            LoginResult result = memberService.authenticate(dto);

            // then
            assertEquals(LoginResult.SUCCESS, result);
        }

}