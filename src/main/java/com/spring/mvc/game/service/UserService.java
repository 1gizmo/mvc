package com.spring.mvc.game.service;

import com.spring.mvc.game.entity.User;
import com.spring.mvc.game.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userRepository;

    public List<User> getList(User userName){
        return userRepository.findAll(userName)
                .stream()
                .map(user->new User(user))
                .collect(toList())
                ;
    }
    public boolean delete(String userName){
        return userRepository.userDelete(userName);
    }

    public  User userDetail(String userName){
        User user = userRepository.findOne(userName);
        return new User(user);
    }

    public boolean register(User user){
        return userRepository.userSave(new User(user));
    }



}
