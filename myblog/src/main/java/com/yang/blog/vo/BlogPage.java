package com.yang.blog.vo;


import lombok.Data;
import lombok.ToString;

import java.util.Date;


/**
 * 用来查询出所有博客列表进行前端展示的vo
 */
@Data
@ToString
public class BlogPage {

    //博客信息
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private String description;
    private Date createTime;

    //分类名称
    private String typeName;

    //用户名
    private String nickname;

    //用户头像
    private String headImg;

}
