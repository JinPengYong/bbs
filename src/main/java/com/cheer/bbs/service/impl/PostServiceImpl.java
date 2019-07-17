package com.cheer.bbs.service.impl;

import com.cheer.bbs.dao.PostMapper;
import com.cheer.bbs.model.Post;
import com.cheer.bbs.model.PostVo;
import com.cheer.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional// 声明事务 将该类下所有的公有方法都设置为事务方法
public class PostServiceImpl implements PostService {

    @Resource
    private PostMapper postMapper;


    @Override
    public List<PostVo> getList() {
        return postMapper.getList();
    }

    @Override
    public Post getPost(Integer postId) {
        return postMapper.getPost(postId);
    }

    @Override
    public int insert(Post post) {
        return postMapper.insert(post);
    }

    @Override
    public int update(Post post) {
        return postMapper.update(post);
    }

    @Override
    public List getReplyIdList() {

        return postMapper.getReplyIdList();
    }

    @Override
    public int getCount(Integer userId) {
        return postMapper.getCount(userId);
    }

    @Override
    public List<Post> getUserList(Integer userId) {
        return postMapper.getUserList(userId);
    }

}
