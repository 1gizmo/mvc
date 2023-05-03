package com.spring.mvc.game.repository;

import com.spring.mvc.game.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

        // 전체 유저 목록
       List<User> findAll(User user);

       // 유저 정보 상세조회
        User findOne(String userName);

        // 유저 추가
        boolean userSave(User user);

        // 유저 삭제
        boolean userDelete(String userName);


}
