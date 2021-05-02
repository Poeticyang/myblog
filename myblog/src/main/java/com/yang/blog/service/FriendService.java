package com.yang.blog.service;

import com.yang.blog.entity.Friend;

import java.util.List;

public interface FriendService {

    //新增友链
    int saveFriend(Friend friend);

    //删除友链
    int deleteFriend(Long id);

    //修改友链
    int updateFriend(Friend friend);

    //查询所有友链
    List<Friend> getAllFriends();

    //根据id查询友链
    Friend getFriendById(Long id);

    //根据地址查询友链
    Friend getFriendByAddress(String address);
}
