package com.yang.blog.controller.admin;


import com.yang.blog.entity.User;
import com.yang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 请求/admin时，跳转到登录页面
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }


    /**
     * 登录检查，post请求
     */
    @PostMapping("/login")
    public String login(@RequestParam String userName,
                        @RequestParam String passWord,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(userName,passWord);
        if (user != null){//登录成功
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {   //登录失败
            attributes.addFlashAttribute("msg","用户名或密码错误");
            return "redirect:/admin";
        }
    }


    @GetMapping("logout")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
