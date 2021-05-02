package com.yang.blog.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BlogRecommend {

    private Long id;
    private String title;
    private String firstPicture;
    private boolean recommend;

}
