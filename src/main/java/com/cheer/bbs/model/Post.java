package com.cheer.bbs.model;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    private Integer postId;
    private String theme;
    private String content;
    private String postTime;
    private Integer userId;
    private String replyTime;
    private Integer replyId;
}
