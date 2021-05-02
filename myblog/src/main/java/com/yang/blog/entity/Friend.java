package com.yang.blog.entity;

import lombok.Data;
import lombok.ToString;
import java.util.Date;

/**
 * 友链实体类
 */

@Data
@ToString
public class Friend {
    private Long id;
    private String blogAddress;
    private String blogName;
    private Date createTime;
    private String pictureAddress;
}
