package com.cheer.bbs.dao;

import com.cheer.bbs.model.User;

public interface UserMapper {
    int insert(User user);

    User find(String username);

    String getUsername(Integer userId);

    User getUser(Integer userId);
}
