package com.yang.blog.service;


import com.yang.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    //根据博客id查询所有评论信息
    List<Comment> listCommentByBlogId(Long blogId);

    //添加评论
    int saveComment(Comment comment);

    //删除评论
    int deleteComment(Comment comment,Long id);

    /*//根据id查询出某个评论
    Comment getCommentByParentCommentId(Long parentCommentId);
*/
}
