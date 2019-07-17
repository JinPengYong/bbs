package com.cheer.bbs.web.controller;

import com.cheer.bbs.model.*;
import com.cheer.bbs.service.PostService;
import com.cheer.bbs.service.ReplyService;
import com.cheer.bbs.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Log4j2
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReplyService replyService;

    @GetMapping("homePage")
    public String homePage(Model model) {
        List<PostVo> postVoList = postService.getList();

        model.addAttribute("postVoList", postVoList);
        return "homePage";
    }

    @GetMapping("blogPage")
    public String blogPage(Model model, Integer postId, HttpSession session) {
        Post post = postService.getPost(postId);
        Integer userId = post.getUserId();
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        model.addAttribute("post", post);
        System.out.println(postId);
        session.setAttribute("postId", postId);
        List<ReplyVo> list = replyService.getList(postId);
        model.addAttribute("replyList", list);
        return "blogPage";
    }

    @PostMapping("sendPost")
    public String sendPost(HttpSession session, Post post) {
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String format = sd.format(date);
        User user = (User) session.getAttribute("user");
        Integer userId = user.getUserId();
        post.setUserId(userId);
        post.setPostTime(format);
        post.setReplyTime(format);
        post.setReplyId(userId);
        postService.insert(post);
        return "redirect:/homePage";
    }

    @GetMapping("userHomePage")
    public String userPage(Integer replyId, Model model, HttpSession session) {
        List<Post> userPostList = postService.getUserList(replyId);
        model.addAttribute("userPostList", userPostList);
        User user = (User) session.getAttribute("user");
        if (replyId == user.getUserId()) {
            return "redirect:/personalPage";
        }
        User user1 = userService.getUser(replyId);

        model.addAttribute("user", user1);
        return "userHomePage";
    }
}
