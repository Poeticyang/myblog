package com.yang.blog.vo;


import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 查看博客详情的vo
 */
@Data
@ToString
public class BlogDetail {

    //博客信息
    private Long id;
    private String firstPicture;
    private String flag;
    private String title;
    private String content;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private boolean commentabled;
    private boolean shareStatement;
    private boolean appreciation;
    private String nickname;
    private String headImg;

    //分类名称
    private String typeName;

}
