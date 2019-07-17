package com.cheer.bbs.dao;

import com.cheer.bbs.model.Reply;
import com.cheer.bbs.model.ReplyVo;

import java.util.List;


public interface ReplyMapper {
    int insert(Reply reply);

    List<ReplyVo> getList(Integer postId);

    int getCount(Integer postId);

    int getReplyCount(Integer userId);

    List<Reply> getUserReplyList(Integer userId);

    List<Reply> getUserReplyList1(Integer userId, Integer postId);


}
