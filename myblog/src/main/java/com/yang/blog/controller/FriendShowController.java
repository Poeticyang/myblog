package com.yang.blog.controller;

import com.yang.blog.entity.Friend;
import com.yang.blog.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FriendShowController {

    @Autowired
    FriendService friendService;

    //跳转到友链页，在此之前，查询出所有友链在前端展示
    @GetMapping("/friends")
    public String friendPage(Model model){

        List<Friend> friends = friendService.getAllFriends();
        model.addAttribute("friends",friends);
        return "friends";
    }

}
