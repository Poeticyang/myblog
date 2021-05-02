package com.yang.blog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 照片实体类
 */
@Data
@ToString
public class Picture {

    private Long id;
    private String pictureAddress;
    private String pictureDescription;
    private String pictureName;
    private String pictureTime;

}
