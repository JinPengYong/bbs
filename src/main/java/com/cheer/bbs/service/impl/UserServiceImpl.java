package com.cheer.bbs.service.impl;

import com.cheer.bbs.dao.UserMapper;
import com.cheer.bbs.model.User;
import com.cheer.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional// 声明事务 将该类下所有的公有方法都设置为事务方法
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User find(String username) {
        return userMapper.find(username);
    }

    @Override
    public String getUsername(Integer userId) {
        return userMapper.getUsername(userId);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.getUser(userId);
    }


}
