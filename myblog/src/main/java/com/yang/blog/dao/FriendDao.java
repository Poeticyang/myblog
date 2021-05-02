package com.yang.blog.dao;

import com.yang.blog.entity.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendDao {

    //新增友链
    int saveFriend(Friend friend);

    //删除友链
    int deleteFriend(Long id);

    //修改友链
    int updateFriend(Friend friend);

    //查询出所有友链
    List<Friend> getAllFriends();

    //根据id查询出友链
    Friend getFriendById(Long id);

    //根据地址查询出友链
    Friend getFriendByAddress(String address);

}
