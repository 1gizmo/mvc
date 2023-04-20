package com.spring.mvc.chap04.controller;

import com.spring.mvc.chap04.DTO.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import com.spring.mvc.chap04.repository.ScoreRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
 * 요청 URL
 * 1. 학생 성적정보 등록 및 조회 처리
 * - /socre/List : GET (조회 )
 *
 * 2. 성적 정보 처리 요청
 * - /score/register : POST
 *
 * 3. 성적정보 삭제 요청
 * - /score/remove : POST
 *
 * 4. 성적정보 상제 조회 요청
 * - /score/detail : GET
 *
 */
@Controller
@RequestMapping("/score")
//@AllArgsConstructor    //  : 모든 필드를 초기화 하는 생성자
@RequiredArgsConstructor // : final 필드만 초기화하는 생성자
public class ScoreController {

    // 저장소에 의존해야 데이터를 받아서 클리언트에게 응답할 수 있음
    // 1번 리스트 목록 보여주기 위해서 목록 가져오기
    private final ScoreRepository repository;

    // 만약에 클래스의 생성자가 단 1개라면 자동으로 @Autowired를  써줌


    // 주입 객체 불변성을 위해 final
    // @AllArgs
//    @Autowired
//    public ScoreController(ScoreRepository repository) {
//        this.repository = repository;
//    }

    // 1. 성적등록화면 띄우기 + 정보목록조회
    @GetMapping("/list")
    public String list(Model model, String sort) {
        System.out.println("/score/List : GET ! ");
        List<Score> scoreList = repository.findAll();
        model.addAttribute("sList", scoreList);
        return "chap04/score-list";
    }


    // 2. 성적 정보 처리요청
    @PostMapping("/register")
    public String register(ScoreRequestDTO dto) {

        // 입력데이터 ( 쿼리스트링 ) 읽기
        System.out.println("/score/register : POST ! " + dto);
        // dto(ScoreDTO)를 entity(Score)로 변환해야 함.
        Score score = new Score(dto);

        // save 명령
        repository.save(score);
        /*
         *  등록 요청에서 JSP 뷰 포워딩을 하면 갱신된 목록을
         *  다시한번 저장소에서 불러와 모델에 담는 추가적인 코드가 필요하지만
         *  리다이렉트를 통해서 위에서 만든 /score/lIst : GET 요청을
         *  다시 자동으로 보낼 수 있다면 번거로운 코드가 줄어들 수 있겠다
         */

        return "redirect:/score/list";
    }

    // 3. 성적정보 삭제 요청
    @GetMapping("/remove")
    public String remove(int stuNum) {
        System.out.println("/score/remove GET ! ");

        repository.deleteByStuNum(stuNum);

        return "redirect:/score/list";
    }

    // 4. 성적정보 상세 조회 요청
    @GetMapping("/detail")
    public String detail(int stuNum, Model model) {
//        repository.findByStuNum(stuNum);

        System.out.println("/score/detail : GET !");

        Score score = repository.findByStuNum(stuNum);
        model.addAttribute("s", score);

        return "chap04/score-detail";
    }
    // 수정
    @GetMapping("/modify")
    public String modify(Score score){

        repository.change(score);


        return "chap04/score-modify";
    }

    @PostMapping("/change")
    public String change(Score score, Model model){

        String ch = repository.change(score);

        model.addAttribute("s", ch);

        return "redirect:/score/detail";
    }

}
