package com.yang.blog.service.impl;

import com.yang.blog.dao.BlogDao;
import com.yang.blog.dao.CommentDao;
import com.yang.blog.entity.Comment;
import com.yang.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论功能业务层
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Autowired
    BlogDao blogDao;

    //存放迭代找出所有子回复的集合
    private List<Comment> replyList = new ArrayList<>();

    /**
     * 根据博客id查询出所有评论信息
     */
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //找出所有的父评论
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId,Long.parseLong("-1"));

        for (Comment comment:comments){

            Long id = comment.getId();
            String parentNickName1 = comment.getNickname();
            List<Comment> childComments = commentDao.findByBlogIdParentNotNull(blogId,id);
            combineChildren(blogId,childComments,parentNickName1);
            comment.setReplyComments(replyList);
            replyList = new ArrayList<>();
        }
        return comments;
    }

    //查询出一级回复
    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickName1) {

        if (childComments.size()>0){
            for (Comment childComment:childComments){

                String parentNickName = childComment.getNickname();
                childComment.setParentNickName(parentNickName1);
                replyList.add(childComment);
                Long childId = childComment.getId();

                //递归查询二级评论
                recursively(blogId,childId,parentNickName);


            }
        }

    }

    //递归查询二级回复
    private void recursively(Long blogId, Long childId, String parentNickName1) {

        List<Comment> replayComments = commentDao.findByBlogIdAndReplayId(blogId,childId);
        if (replayComments.size()>0){

            for (Comment replayComment:replayComments){

                String parentNickName = replayComment.getNickname();
                replayComment.setParentNickName(parentNickName1);
                Long replayId = replayComment.getId();
                replyList.add(replayComment);
                recursively(blogId,replayId,parentNickName);

            }

        }

    }

    /**
     * 添加评论
     */
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int count = commentDao.saveComment(comment);
        blogDao.getCommentCountById(comment.getBlogId());

        return count;
    }

    /**
     * 删除评论
     */
    @Override
    public int deleteComment(Comment comment,Long id) {
        int count = commentDao.deleteComment(id);
        blogDao.getCommentCountById(comment.getBlogId());
        return count;
    }

    /**
     * 根据id查询出某个评论
     */
   /* @Override
    public Comment getCommentByParentCommentId(Long id) {
        return commentDao.getCommentByParentCommentId(id);
    }*/
}
