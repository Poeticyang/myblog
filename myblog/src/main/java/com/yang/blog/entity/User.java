package com.yang.blog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
@ToString
public class User {

    private Long id;
    private String headImg;
    private Date createTime;
    private String email;
    private String nickname;
    private String password;
    private String type;
    private Date updateTime;
    private String username;


}
