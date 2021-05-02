package com.yang.blog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论实体类
 */
@Data
@ToString
public class Comment {
    private Long id;
    private String nickname;//昵称
    private String email;
    private String content;
    private String headImg;
    private Date createTime;
    private Long blogId;
    private Long parentCommentId;
    private boolean adminComment;

    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
    private String parentNickName;


}
