package com.yang.blog.dao;


import com.yang.blog.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface MessageDao {

    //查询出所有父留言
    List<Message> findByParentIdNull(@RequestParam("parentId") Long parentId);

    //查询一级回复
    List<Message> findByParentIdNotNull(@RequestParam("id") Long id);

    //查询所有二级子回复
    List<Message> findByReplayId(@RequestParam("childId") Long childId);

    //添加留言
    int saveMessage(Message message);

    //删除留言
    int deleteMessage(Long id);


}
