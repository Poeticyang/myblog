package com.yang.blog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.blog.entity.Type;
import com.yang.blog.service.BlogService;
import com.yang.blog.service.TypesService;
import com.yang.blog.vo.BlogPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {


    @Autowired
    TypesService typesService;

    @Autowired
    BlogService blogService;

    //跳转到分类页面，在之前查询出所有分类分页展示到前端
    @GetMapping("/types/{id}")
    public String typePage(Model model,
                           @PathVariable Long id,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){

        List<Type> types = typesService.getAllTypeAndBlog();

        //id为-1表示是从首页进入到分类页
        if (id == -1){
            //默认显示第一个分类的博客列表
            id = types.get(0).getId();
        }

        model.addAttribute("types",types);

        PageHelper.startPage(pageNum,1000);
        List<BlogPage> blogs = blogService.getBlogsByTypeId(id);
        PageInfo<BlogPage> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);

        return "types";

    }

}
