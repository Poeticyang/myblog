package com.yang.blog.controller;


import com.yang.blog.entity.Comment;
import com.yang.blog.entity.User;
import com.yang.blog.service.BlogService;
import com.yang.blog.service.CommentService;
import com.yang.blog.vo.BlogDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;


    //查询评论列表
    @GetMapping("/comments/{blogId}")
    public String listComments(@PathVariable Long blogId, Model model){

        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "blog::commentList";

    }

    //新增评论
    @PostMapping("/comments")
    public String saveComment(Comment comment, HttpSession session,Model model){
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");

        //管理员登录
        if (user != null){
            comment.setHeadImg(user.getHeadImg());
            comment.setAdminComment(true);
        } else {
            comment.setHeadImg(avatar);
        }

        /*//parentCommentId如果不等于-1，说明他有父评论，为回复，=-1为父评论
        if (comment.getParentCommentId()!=-1){
            comment.setParentComment(commentService.getCommentByParentCommentId(comment.getParentCommentId()));
            comment.setParentNickName(commentService.getCommentByParentCommentId(comment.getParentCommentId()).getNickname());
        }*/

        /*//说明这个评论有父评论
        if (comment.getParentCommentId() != -1) {
            comment.setParentNickName();
        }*/

        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "blog::commentList";
    }


    //删除评论
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String deleteComment(Comment comment,@PathVariable Long id,@PathVariable Long blogId,Model model){

        commentService.deleteComment(comment,id);
        BlogDetail blogDetail = blogService.getBlogDetail(blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);

        model.addAttribute("blog",blogDetail);
        model.addAttribute("comments",comments);

        return "blog";
    }
}
