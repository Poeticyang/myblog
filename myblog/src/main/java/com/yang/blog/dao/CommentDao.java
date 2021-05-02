package com.yang.blog.dao;


import com.yang.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 评论dao层
 */

@Mapper
@Repository
public interface CommentDao {

    //查询父级评论
    List<Comment> findByBlogIdParentIdNull(@RequestParam("blogId") Long blogId,@RequestParam("blogParentId") Long blogParentId);

    //查询一级回复
    List<Comment> findByBlogIdParentNotNull(@RequestParam("blogId") Long blogId,@RequestParam("id") Long id);

    //查询二级回复
    List<Comment> findByBlogIdAndReplayId(@RequestParam("blogId") Long blogId,@RequestParam("childId") Long childId);

    //添加评论
    int saveComment(Comment comment);

    //删除评论
    int deleteComment(Long id);

    /*//根据父id查询出某个评论
    Comment getCommentByParentCommentId(Long parentCommentId);

    //根据*/



}
