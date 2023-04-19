package com.spring.mvc.chap03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
        1번요청: 로그인 폼 화면 열어주기
        - 요청 URL : /hw/s-login-form : GET
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-form.jsp
        - html form: 아이디랑 비번을 입력받으세요.

        2번요청: 로그인 검증하기
        - 로그인 검증: 아이디를 grape111이라고 쓰고 비번을 ggg9999 라고 쓰면 성공
        - 요청 URL : /hw/s-login-check
        - 포워딩 jsp파일 경로:  /WEB-INF/views/chap03/s-result.jsp
        - jsp에게 전달할 데이터: 로그인 성공여부, 아이디 없는경우, 비번 틀린경우

     */
@Controller
@RequestMapping("/hw")
public class LoginControrller {

    @GetMapping("/s-login-form")
    public String login(){
        System.out.println("/hw/s-login-form GET 요청 발생 ! ");

        return "chap03/s-login-form";
    }
    private String userid = "grape111";
    private String userpw = "ggg999";

    @PostMapping("/s-login-check")
    public String logincheck( String id, String pw, Model model) {
        System.out.println("id = " + id);
        System.out.println("pw = " + pw);
        String login1 = "";

        if(userid==id){
            if (userpw == pw){
                login1 = "성공";
            }else {
             login1 = "비밀번호 틀립니다";
            }
        }else {
             login1 = "아이디가 틀립니다";
        }

        model.addAttribute("login1", login1);



        return "chap03/s-result";
    }
}
