package com.spring.mvc.chap05.entity;

import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
/*
* create table tbl_board (
	board_no int(10) auto_increment primary key,
	title varchar(80) not null,
	content varchar(2000),
	view_count int(10) default 0,
	reg_date_time datetime default current_timestamp
);*/
    private int boardNo; // 게시글 번호
    private String title; // 제목
    private String content; // 내용
    private int viewcount; // 조회수
    private LocalDateTime regdatetime; // 작성일자시간

    public Board(int boardNo, String title, String content) {
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
        this.regdatetime = LocalDateTime.now();
    }

    public Board(BoardWriteRequestDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.regdatetime = LocalDateTime.now();
    }
}
