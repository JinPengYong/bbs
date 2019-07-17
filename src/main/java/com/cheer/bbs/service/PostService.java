package com.cheer.bbs.service;

import com.cheer.bbs.model.Post;
import com.cheer.bbs.model.PostVo;

import java.util.List;

public interface PostService {
    List<PostVo> getList();

    Post getPost(Integer postId);

    int insert(Post post);

    int update(Post post);

    List getReplyIdList();

    int getCount(Integer userId);

    List<Post> getUserList(Integer userId);

}
