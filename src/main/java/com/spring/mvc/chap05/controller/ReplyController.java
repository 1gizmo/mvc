package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.ReplyListResponseDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
@Slf4j
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




}