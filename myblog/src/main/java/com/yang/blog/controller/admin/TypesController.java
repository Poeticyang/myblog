package com.yang.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.blog.entity.Type;
import com.yang.blog.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypesController {

    @Autowired
    TypesService typesService;

    //跳转到分类管理页面，之前分页查询出所有的分类
    @GetMapping("/types")
    public String listTypes(Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        //按照id排序，降序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,10,orderBy);
        //查询出所有分类，跟在startPage后的第一个mybatis查询方法会被分页
        List<Type> list = typesService.getAllTypes();

        //将分页的结果集保存到pageInfo中返回到页面
        PageInfo<Type> info = new PageInfo<>(list);
        model.addAttribute("pageInfo",info);
        return "admin/types";
    }


    //跳转到新增分类页面
    @GetMapping("/typesInsert")
    public String saveTypePage(Model model){
        //建好Type对象
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }


    //新增分类
    @PostMapping("/typesInsert")
    public String saveType(Type type, RedirectAttributes attributes){
        //type：id=null ,name=页面中填写的
        Type type1 = typesService.getTypeByName(type.getName());
        if (type1 != null){
            attributes.addFlashAttribute("message","该分类已存在！");
            return "redirect:/admin/typesInsert";
        }
        int i = typesService.saveType(type);
        if (i == 0){
            attributes.addFlashAttribute("message","添加失败，请重试");
        }else {
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/types";
    }


    //跳转到编辑分类页面
    @GetMapping("/typesUpdate/{id}/input")
    public String updateTypePage(@PathVariable Long id, Model model){

        //type，id=编辑页面之前types页面传过来的，name=表单填写的
        model.addAttribute("type",typesService.getTypeById(id));
        return "admin/types-update";
    }

    //编辑分类
    @PostMapping("/typesUpdate")
    public String updateType(Type type,RedirectAttributes attributes){

        //type: id为跳转到编辑页面时传过来的id，上面的方法。name=表单填写的
        Type type1 = typesService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "该名称已存在");
            return "redirect:/admin/types";
        }
        int t = typesService.updateType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/types";
    }

    //删除分类
    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){

        int count = typesService.deleteType(id);
        if (count != 1){
            attributes.addFlashAttribute("message","删除失败");
        }else {
            attributes.addFlashAttribute("message","删除成功");
        }
        return "redirect:/admin/types";
    }

}
