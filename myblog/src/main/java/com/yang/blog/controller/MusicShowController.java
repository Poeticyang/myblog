package com.yang.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MusicShowController {

    //跳转到音乐盒页面
    @GetMapping("/music")
    public String musicPage(){
        return "music";
    }


}
