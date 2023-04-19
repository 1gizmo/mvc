package com.spring.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

// 어떤 요청들을 처리할지 공통 URL을 설계
@RequestMapping("/spring/*")
// 이 클래스의 객체를 스프링이 관리하도록 빈을 등록
@Controller // @Component와 같은 개념
public class ControllerV1 {

    // 세부요청들은 메서드를 통해 처리
    @RequestMapping("/hello") //  http://localhost:8181/spring/hello
    public String hello() {
        System.out.println("\n====== 헬로 요청이 들어옴! =====\n");
        // 어떤 JSP를 열어줄지 경로를 적습니다.
        return "hello";
    }

    @RequestMapping("/food")
    public String food(){
        // "/WEB-INF/views/ .... / ... jsp 생략가능
        return "/chap01/food";
    }

    // =============== 요청 파라미터 읽기 ( Query String parameter ) ===================
    // 1. HttpServletRequest 사용하기
    // ====> ex )  /spring/person?name=kim&age=30
    @RequestMapping("/person")
    public String person(HttpServletRequest request){
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        return "/";
    }

    // == 2. @RequestParam 사용하기
    // ==> ex ) /spring/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    public String major(
            String stu, // default 변경도 안하고 stu값이 동일하다면 requestparam 생략가능
            @RequestParam ("major")String mj,
            @RequestParam (defaultValue = "1")int grade
    ) {
        String major = "전공전공";

        System.out.println("stu = " + stu);
        System.out.println("major = " + mj);
        System.out.println("grade = " + grade);
        return "";
    }

    // === 3. 커맨드 객체 이용하기
    // == 쿼리 스트링의 양이 너무 많을 경우 또는 연관성이 있을경우
    // ==> ex ) /spring/order?oNum=20230419007-P&goods=구두&amount=3&price=300000
    // DTO
    @RequestMapping("/order")
    public String order(OrderRequestDTO dto) {
        System.out.println("dto = " + dto);
        return "";
    }

    // == 4. URL에 경로로 붙어있는 데이터 읽기
    // ==> /spring/member/hong/107
    // hong 이라는 유저의 107번 게시글을 읽고싶음
    @RequestMapping("/member/{userName}/{bNo}")
    public String member(
       @PathVariable String userName,
       @PathVariable long bNo
       //@PathVariable 은 생략하면 안됨 생략하면 @RequestParam으로 읽음
    ) {
        System.out.println("userName = " + userName);
        System.out.println("bNo = " + bNo);
        return "";
    }

    // 400대 에러 클라이언트 잘못
    // 500대 에러 서버 잘못

    // 음식 선택 요청처리
//    @RequestMapping(value = "/food-select" , method = RequestMethod.POST)
    // value 와 method 설정으로 Post 방식이 아니면 처리하지 않도록 설정
    @PostMapping("/food-select") // 위에 긴 설정보다 간단하게 설정가능
    public String foodSelect(
            String foodName,
            String category
    ) {
        System.out.println("foodName = " + foodName);
        System.out.println("category = " + category);
        return "";
    }

}