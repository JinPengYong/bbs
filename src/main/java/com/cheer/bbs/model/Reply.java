package com.cheer.bbs.model;

import lombok.Data;

@Data
public class Reply {
    private Integer id;
    private Integer userId;
    private String content;
    private String time;
    private Integer postId;
    private Integer num;
}
