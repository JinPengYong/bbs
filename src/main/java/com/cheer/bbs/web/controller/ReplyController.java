package com.cheer.bbs.web.controller;

import com.cheer.bbs.model.Post;
import com.cheer.bbs.model.Reply;
import com.cheer.bbs.model.User;
import com.cheer.bbs.service.PostService;
import com.cheer.bbs.service.ReplyService;
import com.cheer.bbs.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Log4j2
public class ReplyController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private ReplyService replayService;

    @PostMapping("reply")
    public String insert(String content, Reply reply, HttpSession session) {
        System.out.println(content);
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        reply.setUserId(userId);
        reply.setContent(content);
        Integer postId = (Integer) session.getAttribute("postId");
        reply.setPostId(postId);
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String format = sd.format(date);
        reply.setTime(format);
        int count = replayService.getCount(postId);
        reply.setNum(count + 1);
        Post post = postService.getPost(postId);
        post.setReplyTime(format);
        post.setReplyId(userId);
        postService.update(post);
        replayService.insert(reply);
        return "redirect:/blogPage?postId=" + postId;
    }
}
