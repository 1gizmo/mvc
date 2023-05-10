package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.ReplyModifyRequestDTO;
import com.spring.mvc.chap05.dto.ReplyPostRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
@Slf4j
//클라이언트의 접근을 어떤 app에서만 허용할 것인가
@CrossOrigin(origins = {"http://127.0.0.1:5501"})
public class ReplyController {

    private final ReplyService replyService;

    // 댓글 목록 조회 요청  //select get / insert post
    // URL : /api/v1/replies/3/page/1
    // 3 번 게시물 댓글목록 1페이지 내놔
    @GetMapping("/{boardNo}/page/{pageNo}")
    public ResponseEntity<?> list(
            @PathVariable long boardNo,
            @PathVariable int pageNo
    ) {
        log.info("/api/v1/replies/{}/page/{} : GET !! ", boardNo, pageNo);
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setAmount(10);
        ReplyListResponseDTO replyList = replyService.getList(boardNo, page);

        return ok().body(replyList);
    }

    //댓글 등록 요청
    @PostMapping
    public ResponseEntity<?> create(
            //  @requestbody : 요청 메세지 바디에 JSON 으로 보내주세요 ~
         @Validated @RequestBody ReplyPostRequestDTO dto
            , BindingResult result // 검증결과를 가진 객체
    ) {
        // 입력값 검증에 걸리면 4xx 상태코드 리턴
        if(result.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }
        log.info("/api/v1/replies : POST ! ");

        // 입력값이 넘어오는지 확인
        log.info("param: {}", dto);
        //서비스에 비즈니스 로직 처리 위임
        try {
            ReplyListResponseDTO registerDTO = replyService.register(dto);
            // 성공시 클라이언트에 응답하기
            return ok().body(registerDTO);
        } catch (Exception e) {
            // 문제 발생 상황을 클라이언트에게 전달
            log.warn("500 Status code response !! caused by {} ", e.getMessage());
            return internalServerError()
                    .body(e.getMessage());
        }

    }

    //댓글 삭제 요청
    @DeleteMapping("/{replyNo}")
    public ResponseEntity<?> remove(
           @PathVariable(required = false) Long replyNo
    ) {
        if(replyNo == null){
            return ResponseEntity.badRequest().body("댓글 번호를 보내줘 제발 !!!!!! ");
        }
        log.info("/api/v1/replies/{} DELETE ! ", replyNo);
        try {
            ReplyListResponseDTO delete = replyService.delete(replyNo);
            return ResponseEntity
                    .ok()
                    .body(delete);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    // 댓글 수정 요청
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> modify(
            @Validated @RequestBody ReplyModifyRequestDTO dto
            , BindingResult result
    ) {

        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }

        log.info("/api/v1/replies PUT!");
        try {
            ReplyListResponseDTO responseDTO = replyService.modify(dto);
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            log.warn("500 status code! caused by: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
