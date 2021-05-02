package com.yang.blog.controller;


import com.yang.blog.entity.Message;
import com.yang.blog.entity.User;
import com.yang.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @Value("${message.avatar}")
    private String messageImg;


    //跳转到留言页面
    @GetMapping("/message")
    public String messagePage(){
        return "message";
    }


    //查询出所有留言,局部刷新
    @GetMapping("/messagecomment")
    public String message(Model model){
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages",messages);
        return "message::messageList";
    }


    //新增留言
    @PostMapping("/message")
    public String saveMessage(Message message, HttpSession session,Model model){
        User user  = (User) session.getAttribute("user");
        //管理员
        if (user != null){
            message.setHeadImg(user.getHeadImg());
            message.setAdminMessage(true);
        //游客
        } else {
            message.setHeadImg(messageImg);
        }

        messageService.saveMessage(message);

        //添加完局部刷新messageList模块
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages",messages);
        return "message::messageList";
    }


    //删除留言
    @GetMapping("/message/{id}/delete")
    public String deleteMessage(@PathVariable Long id, RedirectAttributes attributes){
        messageService.deleteMessage(id);
        return "redirect:/message";
    }


}
