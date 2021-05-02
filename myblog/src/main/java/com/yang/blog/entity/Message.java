package com.yang.blog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 留言实体类
 */
@Data
@ToString
public class Message {

    private Long id;
    private String nickname;//昵称
    private String email;
    private String content;
    private String headImg;
    private Date createTime;
    private Long parentMessageId;
    private boolean adminMessage;

    //回复留言
    private List<Message> messages = new ArrayList<>();
    private Message parentMessage;
    private String parentNickname;
}
