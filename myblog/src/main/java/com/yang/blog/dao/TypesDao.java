package com.yang.blog.dao;


import com.yang.blog.entity.Type;
import com.yang.blog.vo.BlogPage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypesDao {

    //列出所有分类
    List<Type> getAllTypes();

    //根据id查询分类
    Type getTypeById(Long id);

    //根据分类名称查询分类
    Type getTypeByName(String name);

    //新增分类
    int saveType(Type type);

    //修改分类
    int updateType(Type type);

    //删除分类
    int deleteType(Long id);




    //查询所有分类
    List<Type> getAllTypeAndBlog();



}
