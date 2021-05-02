package com.yang.blog.service.impl;

import com.yang.blog.dao.MessageDao;
import com.yang.blog.entity.Message;
import com.yang.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    //用来存放迭代出来的留言集合
    private List<Message> tempMessages = new ArrayList<>();
    /**
     * 查询出所有留言
     */
    @Override
    public List<Message> listMessage() {

        //找出父节点,父留言id为-1表示自己就是顶级/父留言
        List<Message> messages = messageDao.findByParentIdNull(Long.parseLong("-1"));
        //循环找出每个父留言下的子回复
        for (Message message:messages){
            //拿出当前父节点的id，作为父id找下面的回复
            Long id = message.getId();
            //拿出当前父节点的昵称
            String parentNickName1 = message.getNickname();
            //找出以当前父节点id为父id的一级回复
            List<Message> childMessages = messageDao.findByParentIdNotNull(id);

            combineChildren(childMessages,parentNickName1);

            message.setMessages(tempMessages);
            tempMessages = new ArrayList<>();
        }

        return messages;
    }




    private void combineChildren(List<Message> childMessages, String parentNickName1) {

        //判断是否有一级回复
        if (childMessages.size()>0){

            //循环所有子回复
            for (Message childMessage:childMessages){
                //取出当前子回复的昵称
                String parentNickName = childMessage.getNickname();
                //设置的当前循环的子回复的父节点昵称
                childMessage.setParentNickname(parentNickName1);
                //将当前循环的子回复放入到临时集合中
                tempMessages.add(childMessage);
                Long childId = childMessage.getId();
                replay(childId,parentNickName);


            }
        }

    }

    /**
     * 循环迭代找出所有子回复
     */
    private void replay(Long childId, String parentNickName1) {

        //根据一级回复找到子集回复
        List<Message> replayMessages = messageDao.findByReplayId(childId);

        //如果有子回复
        if (replayMessages.size()>0){

            for (Message replayMessage:replayMessages){

                String parentNickName = replayMessage.getNickname();
                replayMessage.setParentNickname(parentNickName1);
                Long replayId = replayMessage.getId();

                tempMessages.add(replayMessage);

                //循环迭代找出所有子回复
                replay(replayId,parentNickName);
            }

        }

    }


    /**
     * 添加留言
     */
    @Override
    public int saveMessage(Message message) {
        message.setCreateTime(new Date());
        return messageDao.saveMessage(message);
    }

    /**
     * 删除留言
     */
    @Override
    public int deleteMessage(Long id) {
        return messageDao.deleteMessage(id);
    }
}
