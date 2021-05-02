package com.yang.blog.vo;

import com.yang.blog.entity.Type;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * vo
 * 封装查询出来显示的博客
 */
@Data
@ToString
public class BlogQuery {
    private Long id;
    private String title;
    private Date updateTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;
}
