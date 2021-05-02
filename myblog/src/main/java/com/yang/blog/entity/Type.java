package com.yang.blog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类实体类
 */
@Data
@ToString
public class Type {
    private Long id;
    private String name;
    private List<Blog> blogs = new ArrayList<>();
}
