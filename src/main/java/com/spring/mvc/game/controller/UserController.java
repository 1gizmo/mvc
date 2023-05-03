package com.spring.mvc.game.controller;

import com.spring.mvc.game.entity.User;
import com.spring.mvc.game.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public String list(User user, Model model){
        log.info("/user/list : GET ! ");
        List<User> responseUsers = userService.getList(user);
        model.addAttribute("ulist",responseUsers);
        return "game/list";
    }

    @PostMapping("/write")
    public String  write(){
        System.out.println("/user/write : GET ! ");
        return "game/write";
    }

    @GetMapping("/delete")
    public String delete(String userName, Model model){
        System.out.println("/game/delte : GET ! ");
        userService.delete(userName);
        return "redirect:/game/list";
    }

    @GetMapping("/detail")
    public String detail(String userName, Model model){
        model.addAttribute("b",  userService.userDetail(userName));
        return "game/detail";
    }


}
