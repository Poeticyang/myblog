package com.yang.blog.vo;


import lombok.Data;
import lombok.ToString;

/**
 * 用来搜索博客用的博客搜索vo
 */
@Data
@ToString
public class BlogSearch {

    private String title;
    private Long typeId;

}
