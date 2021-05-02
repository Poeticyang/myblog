package com.yang.blog.service.impl;

import com.yang.blog.dao.PictureDao;
import com.yang.blog.entity.Picture;
import com.yang.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureDao pictureDao;

    //查询出所有照片
    @Override
    public List<Picture> getAllPictures() {
        return pictureDao.getAllPictures();
    }

    //新增照片
    @Override
    public int savePicture(Picture picture) {
        return pictureDao.savePicture(picture);
    }

    //修改照片
    @Override
    public int updatePicture(Picture picture) {
        return pictureDao.updatePicture(picture);
    }

    //删除照片
    @Override
    public int deletePicture(Long id) {
        return pictureDao.deletePicture(id);
    }

    //根据id查询照片
    @Override
    public Picture getPictureById(Long id) {
        return pictureDao.getPictureById(id);
    }
}
