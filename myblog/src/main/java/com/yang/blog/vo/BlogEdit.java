package com.yang.blog.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 博客显示vo类，用来传到前端，进行博客的修改功能
 */
@Data
@ToString
public class BlogEdit {
    private Long id;
    private String flag;
    private String title;
    private String content;
    private Long typeId;
    private String firstPicture;
    private String description;
    private boolean recommend;
    private boolean published;
    private boolean shareStatement;
    private boolean appreciation;
    private boolean commentabled;
    private Date updateTime;

}
