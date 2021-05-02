package com.yang.blog.service;

import com.yang.blog.entity.Type;
import com.yang.blog.vo.BlogPage;

import java.util.List;

public interface TypesService {

    //新增分类
    int saveType(Type type);

    //根据id查询分类
    Type getTypeById(Long id);

    //查询所有分类
    List<Type> getAllTypes();

    //根据分类名称查询分类
    Type getTypeByName(String name);

    //修改分类
    int updateType(Type type);

    //删除分类
    int deleteType(Long id);


    //获取所有分类和博客列表
    List<Type> getAllTypeAndBlog();

}
