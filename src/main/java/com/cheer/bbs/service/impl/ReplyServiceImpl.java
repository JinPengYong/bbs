package com.cheer.bbs.service.impl;

import com.cheer.bbs.dao.ReplyMapper;
import com.cheer.bbs.model.Reply;
import com.cheer.bbs.model.ReplyVo;
import com.cheer.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional// 声明事务 将该类下所有的公有方法都设置为事务方法
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replayMapper;
    @Override
    public int insert(Reply replay) {
        return replayMapper.insert(replay);
    }

    @Override
    public List<ReplyVo> getList(Integer postId) {
        return replayMapper.getList(postId);
    }

    @Override
    public int getCount(Integer postId) {

        return replayMapper.getCount(postId);
    }

    @Override
    public int getReplyCount(Integer userId) {
        return replayMapper.getReplyCount(userId);
    }

    @Override
    public List<Reply> getUserReplyList(Integer userId) {
        return replayMapper.getUserReplyList(userId);
    }

    @Override
    public List<Reply> getUserReplyList1(Integer userId, Integer postId) {
        return replayMapper.getUserReplyList1(userId,postId);
    }


}
