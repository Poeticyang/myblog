package com.yang.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.blog.entity.Picture;
import com.yang.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PictureController {

    @Autowired
    PictureService pictureService;

    //跳转到照片管理页面，跳转之前查询出所有的照片显示
    @GetMapping("/pictures")
    public String picturePage(Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){

        PageHelper.startPage(pageNum,10);
        List<Picture> list = pictureService.getAllPictures();

        PageInfo<Picture> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/pictures";
    }

    //跳转到新增照片页面
    @GetMapping("/pictures/input")
    public String savePicturePage(Model model){
        model.addAttribute("picture",new Picture());
        return "admin/pictures-input";
    }

    //新增照片
    @PostMapping("/pictures")
    public String savePicture(Picture picture, RedirectAttributes attributes){
        int count = pictureService.savePicture(picture);
        if (count == 0){
            attributes.addFlashAttribute("message","添加照片失败");
        } else {
            attributes.addFlashAttribute("message","添加照片成功");
        }
        return "redirect:/admin/pictures";
    }

    //跳转到修改照片页面
    @GetMapping("/pictures/{id}/input")
    public String updatePicturePage(@PathVariable Long id,Model model){
        Picture picture = pictureService.getPictureById(id);
        model.addAttribute("picture",picture);
        return "admin/pictures-input";
    }

    //修改照片
    @PostMapping("/pictures/{id}")
    public String updatePicture(Picture picture,RedirectAttributes attributes){
        int count = pictureService.updatePicture(picture);
        if (count == 0 ){
            attributes.addFlashAttribute("message","修改照片失败");
        } else {
            attributes.addFlashAttribute("message","修改照片成功");
        }

        return "redirect:/admin/pictures";

    }

    //删除照片
    @GetMapping("/pictures/{id}/delete")
    public String deletePicture(@PathVariable Long id,RedirectAttributes attributes){
        int count = pictureService.deletePicture(id);
        if (count == 0){
            attributes.addFlashAttribute("message","删除成功");
        } else {
            attributes.addFlashAttribute("message","删除成功");
        }
        return "redirect:/admin/pictures";

    }


}
