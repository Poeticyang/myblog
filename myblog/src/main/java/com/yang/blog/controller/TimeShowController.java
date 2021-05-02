package com.yang.blog.controller;

import com.yang.blog.service.BlogService;
import com.yang.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TimeShowController {

    @Autowired
    BlogService blogService;

    //跳转到时间轴页面，在跳转前查询出所有博客列表展示
    @GetMapping("/time")
    public String timePage(Model model){
        List<BlogQuery> blogs = blogService.getAllBlogsTime();

        model.addAttribute("blogs",blogs);
        return "time";
    }

}
