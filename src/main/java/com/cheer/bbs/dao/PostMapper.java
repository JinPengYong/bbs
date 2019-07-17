package com.cheer.bbs.dao;

import com.cheer.bbs.model.Post;
import com.cheer.bbs.model.PostVo;

import java.util.List;

public interface PostMapper {
    List<PostVo> getList();

    int insert(Post post);

    Post getPost(Integer postId);

    int update(Post post);

    List getReplyIdList();

    int getCount(Integer userId);

    List<Post> getUserList(Integer userId);




}
