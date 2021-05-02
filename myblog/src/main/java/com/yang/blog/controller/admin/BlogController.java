package com.yang.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.blog.entity.Blog;
import com.yang.blog.entity.Type;
import com.yang.blog.entity.User;
import com.yang.blog.service.BlogService;
import com.yang.blog.service.TypesService;
import com.yang.blog.vo.BlogEdit;
import com.yang.blog.vo.BlogQuery;
import com.yang.blog.vo.BlogSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypesService typesService;

    //跳转到博客管理页面，在之前分页查询======
    @GetMapping("/blogs")
    public String blogs(Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        //根据更新时间倒序查询
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,10,orderBy);
        //跟在startPage方法后的第一个mybatis查询会被分页查询
        List<BlogQuery> blogs = blogService.getAllBlogs();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("types",typesService.getAllTypes());
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    //跳转到新增博客页面
    @GetMapping("/blogs/input")
    public String saveBlogPage(Model model){
        model.addAttribute("types",typesService.getAllTypes());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    //新增博客
    @PostMapping("/blogs")
    public String saveBlog(Blog blog, RedirectAttributes attributes, HttpSession session){

        //新增博客时，需要传递user
        User user = (User) session.getAttribute("user");
        blog.setUser(user);

        //设置博客的type
        blog.setType(typesService.getTypeById(blog.getType().getId()));

        //设置博客的typeId
        blog.setTypeId(blog.getType().getId());

        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int count = blogService.saveBlog(blog);

        if (count==0){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/blogs";

    }

    //删除博客
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes){

        int count = blogService.deleteBlog(id);
        if (count == 0){
            attributes.addFlashAttribute("message","删除失败");
        } else {
            attributes.addFlashAttribute("message","删除成功");
        }
        return "redirect:/admin/blogs";

    }

    //跳转到修改文章页面
    @GetMapping("/blogs/{id}/input")
    public String updateBlogPage(@PathVariable Long id,Model model){
        BlogEdit blogEdit = blogService.getBlogById(id);
        List<Type> types = typesService.getAllTypes();
        model.addAttribute("blog",blogEdit);
        model.addAttribute("types",types);
        return "admin/blogs-input";
    }

    //修改博客
    @PostMapping("/blogs/{id}")
    public String updateBlog(BlogEdit blogEdit,RedirectAttributes attributes){
        int count = blogService.updateBlog(blogEdit);
        if (count ==0 ){
            attributes.addFlashAttribute("message","修改失败");
        } else {
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/blogs";
    }

    //搜索博客
    @PostMapping("/blogs/search")
    public String searchBlogs(Model model, BlogSearch blogSearch,
                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){

        //分页查询
        List<BlogQuery> blogs = blogService.searchBlogs(blogSearch);
        PageHelper.startPage(pageNum,10);

        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);

        return "admin/blogs::blogList";

    }


}
