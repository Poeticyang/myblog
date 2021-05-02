package com.yang.blog.dao;


import com.yang.blog.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PictureDao {

    //查询所有照片
    List<Picture> getAllPictures();

    //添加照片
    int savePicture(Picture picture);

    //修改照片
    int updatePicture(Picture picture);

    //删除照片
    int deletePicture(Long id);

    //根据id查询照片
    Picture getPictureById(Long id);

}
