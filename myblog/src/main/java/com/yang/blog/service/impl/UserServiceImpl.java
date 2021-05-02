package com.yang.blog.service.impl;

import com.yang.blog.dao.UserDao;
import com.yang.blog.entity.User;
import com.yang.blog.service.UserService;
import com.yang.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String userName, String passWord) {

        User user =userDao.getUserByNameAndPwd(userName, MD5Utils.code(passWord));
        return user;
    }
}
