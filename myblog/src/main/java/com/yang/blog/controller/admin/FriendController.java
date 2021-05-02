package com.yang.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.blog.entity.Friend;
import com.yang.blog.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class FriendController {

    @Autowired
    FriendService friendService;

    //跳转到友链管理页面，之前查询出所有友链
    @GetMapping("/friend")
    public String friendsPage(Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){

        PageHelper.startPage(pageNum,10);
        List<Friend> list = friendService.getAllFriends();

        PageInfo<Friend> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);

        return "admin/friend";
    }

    //跳转到新增友链页面
    @GetMapping("/friend/input")
    public String saveFriendPage(Model model){
        model.addAttribute("friend",new Friend());
        return "admin/friend-input";
    }

    //新增友链
    @PostMapping("/friend")
    public String saveFriend(Friend friend, RedirectAttributes attributes){
        Friend friend1 = friendService.getFriendByAddress(friend.getBlogAddress());
        if (friend1 != null){
            attributes.addFlashAttribute("message","不能添加相同的网址");
            return "redirect:/admin/friend";
        }
        friend.setCreateTime(new Date());
        int count = friendService.saveFriend(friend);
        if (count == 0 ){
            attributes.addFlashAttribute("message","添加失败");
        } else {
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/friend";

    }

    //跳转到修改友链页面
    @GetMapping("/friend/{id}/input")
    public String updateFriend(@PathVariable Long id, Model model){

        model.addAttribute("friend",friendService.getFriendById(id));

        return "admin/friend-input";
    }

    //修改友链
    @PostMapping("/friend/{id}")
    public String updateFriend(Friend friend,RedirectAttributes attributes){
        int count = friendService.updateFriend(friend);
        if (count == 0){
            attributes.addFlashAttribute("message","修改失败");
        } else {
            attributes.addFlashAttribute("message","修改成功");
        }
        return "redirect:/admin/friend";
    }

    //删除友链
    @GetMapping("/friend/{id}/delete")
    public String deleteFriend(@PathVariable Long id,RedirectAttributes attributes){

        int count = friendService.deleteFriend(id);
        if (count == 0 ){
            attributes.addFlashAttribute("message","删除失败");
        } else {
            attributes.addFlashAttribute("message","删除成功");
        }

        return "redirect:/admin/friend";
    }






}
