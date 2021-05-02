package com.yang.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.blog.service.BlogService;
import com.yang.blog.vo.BlogDetail;
import com.yang.blog.vo.BlogPage;
import com.yang.blog.vo.BlogRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    //跳转到首页,跳转之前查询出4个最新推荐博客，和所有博客列表分页展示
    @GetMapping("/")
    public String index(Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){

        PageHelper.startPage(pageNum,10);
        List<BlogPage> list = blogService.getBlogPage();
        List<BlogRecommend> recommendBlog = blogService.getRecommendBlog();

        PageInfo<BlogPage> pageInfo = new PageInfo<>(list);

        System.out.println("pageInfo:========"+pageInfo);

        model.addAttribute("recommendBlog",recommendBlog);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

    //搜索博客
    @PostMapping("/search")
    public String searchBlogPage(Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam String query){
        PageHelper.startPage(pageNum,1000);
        List<BlogPage> list = blogService.searchBlogPage(query);

        PageInfo<BlogPage> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";

    }

    //统计博客信息
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        Integer blogTotal = blogService.getBlogTotal();
        Integer blogViewTotal = blogService.getBlogViewTotal();
        Integer blogCommentTotal = blogService.getBlogCommentTotal();
        Integer blogMessageTotal = blogService.getBlogMessageTotal();

        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        model.addAttribute("blogMessageTotal",blogMessageTotal);

        return "index :: blogMessage";

    }



    //跳转到博客详情页面
    @GetMapping("/blog/{id}")
    public String blogDetailPage(@PathVariable Long id, Model model){
        BlogDetail blogDetail = blogService.getBlogDetail(id);
        model.addAttribute("blog",blogDetail);
        return "blog";
    }


}
