package com.yang.blog.service;

import com.yang.blog.entity.Picture;

import java.util.List;

public interface PictureService {

    //查询出所有照片
    List<Picture> getAllPictures();

    //新增照片
    int savePicture(Picture picture);

    //修改照片
    int updatePicture(Picture picture);

    //删除照片
    int deletePicture(Long id);

    //根据id查询照片
    Picture getPictureById(Long id);
}
