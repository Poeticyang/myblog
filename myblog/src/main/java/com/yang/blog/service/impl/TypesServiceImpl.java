package com.yang.blog.service.impl;

import com.yang.blog.dao.TypesDao;
import com.yang.blog.entity.Type;
import com.yang.blog.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Transactional: 实现事务操作
 */
@Service
public class TypesServiceImpl implements TypesService {

    @Autowired
    TypesDao typesDao;

    /**
     * 查询出所有分类
     */
    @Override
    public List<Type> getAllTypes() {
        List<Type> types = typesDao.getAllTypes();
        return types;
    }

    /**
     * 根据id查询分类
     */
    @Override
    public Type getTypeById(Long id) {
        Type type = typesDao.getTypeById(id);
        return type;
    }

    /**
     * 根据名称查询分类
     */
    @Override
    public Type getTypeByName(String name) {
        Type type = typesDao.getTypeByName(name);
        return type;
    }

    /**
     * 新增分类
     */
    @Transactional
    @Override
    public int saveType(Type type) {
        int count = typesDao.saveType(type);
        return count;
    }

    /**
     * 修改分类
     */
    @Transactional
    @Override
    public int updateType(Type type) {
        int count = typesDao.updateType(type);
        return count;
    }

    /**
     * 删除分类
     */
    @Transactional
    @Override
    public int deleteType(Long id) {
        int count = typesDao.deleteType(id);
        return count;
    }

    /**
     * 查询所有分类和该分类下的博客数目
     */
    @Override
    public List<Type> getAllTypeAndBlog() {
        return typesDao.getAllTypeAndBlog();
    }
}
