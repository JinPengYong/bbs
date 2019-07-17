package com.cheer.bbs.service;

import com.cheer.bbs.model.User;

public interface UserService {
    User find(String username);

    String getUsername(Integer userId);

    int insert(User user);

    User getUser(Integer userId);
}
