package com.yang.blog.controller;


import com.yang.blog.entity.Picture;
import com.yang.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PictureShowController {

    @Autowired
    PictureService pictureService;


    //跳转到照片墙页，跳转之前查询出所有照片信息
    @GetMapping("/picture")
    public String picturePage(Model model){
        List<Picture> pictures = pictureService.getAllPictures();
        model.addAttribute("pictures",pictures);
        return "picture";
    }
}
