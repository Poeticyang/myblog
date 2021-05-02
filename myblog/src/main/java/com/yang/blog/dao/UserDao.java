package com.yang.blog.dao;

import com.yang.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    //根据用户名和密码查询出来用户
    User getUserByNameAndPwd(@Param("userName") String userName,@Param("passWord") String passWord);

}
