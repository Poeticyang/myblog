package com.yang.blog.service;

import com.yang.blog.entity.User;

public interface UserService {

    //核验登录信息
    User checkUser(String userName,String passWord);
}
