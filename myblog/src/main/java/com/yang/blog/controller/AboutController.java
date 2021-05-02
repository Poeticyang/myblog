package com.yang.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    //跳转到关于我页面
    @GetMapping("/about")
    public String aboutPage(){
        return "about";
    }

}
