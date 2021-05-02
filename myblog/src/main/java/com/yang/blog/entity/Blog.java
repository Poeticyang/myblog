package com.yang.blog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 博客实体类
 */
@Data
@ToString
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;                  //浏览次数
    private Integer commentCount;           //评论次数
    private boolean appreciation;           //赞赏
    private boolean shareStatement;         //版权
    private boolean commentabled;           //评论
    private boolean published;              //发布
    private boolean recommend;              //推荐
    private Date createTime;
    private Date updateTime;
    private String description;

    private Long typeId;
    private Long userId;

    private Type type;
    private User user;
    private List<Comment> comments = new ArrayList<>();


}
