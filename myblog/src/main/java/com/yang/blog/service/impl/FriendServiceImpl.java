package com.yang.blog.service.impl;

import com.yang.blog.dao.FriendDao;
import com.yang.blog.entity.Friend;
import com.yang.blog.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendDao friendDao;

    //新增友链
    @Override
    public int saveFriend(Friend friend) {
        return friendDao.saveFriend(friend);
    }

    //删除友链
    @Override
    public int deleteFriend(Long id) {
        return friendDao.deleteFriend(id);
    }

    //修改友链
    @Override
    public int updateFriend(Friend friend) {
        return friendDao.updateFriend(friend);
    }

    //查询出所有友链
    @Override
    public List<Friend> getAllFriends() {
        return friendDao.getAllFriends();
    }

    //根据id查询出友链
    @Override
    public Friend getFriendById(Long id) {
        return friendDao.getFriendById(id);
    }

    //根据地址查询出友链
    @Override
    public Friend getFriendByAddress(String address) {
        return friendDao.getFriendByAddress(address);
    }
}
