package com.cheer.bbs.web.controller;

import com.cheer.bbs.model.Post;
import com.cheer.bbs.model.Reply;
import com.cheer.bbs.model.ReplyVo;
import com.cheer.bbs.model.User;
import com.cheer.bbs.service.PostService;
import com.cheer.bbs.service.ReplyService;
import com.cheer.bbs.service.UserService;
import com.cheer.bbs.util.IOUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String login(String username, String password, HttpSession session) {
        User user = userService.find(username);
        if (user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            session.setAttribute("user", user);
           /* String dest = session.getServletContext().getRealPath("/static/avatar")+user.getAvatar();
            File file=new File(dest);
            if (!file.exists()) {
                String src=System.getProperty("user.home")+"/avatar/"+user.getAvatar();//写入项目中指定文件夹
                IOUtils.copy(src, dest);//把上传后的文件复制到指定项目文件夹中
            }*/

            return "redirect:/homePage";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("sendPost")
    public String sendPost() {
        return "sendPost";
    }

    @GetMapping("personalPage")
    public String personalPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        int count = postService.getCount(user.getUserId());
        int replyCount = replyService.getReplyCount(user.getUserId());
        List<Post> userPostList = postService.getUserList(user.getUserId());


        List<Reply> userReplyList = replyService.getUserReplyList(user.getUserId());

        List<Post> postList = new ArrayList<Post>();
        for (Reply reply : userReplyList) {
            Integer postId = reply.getPostId();
            Post post = postService.getPost(postId);
            postList.add(post);
        }


        model.addAttribute("replyCount", replyCount);
        model.addAttribute("count", count);
        model.addAttribute("userPostList", userPostList);
        model.addAttribute("postList", postList);
        //model.addAttribute("userReplyList",userReplyList);
        return "personalPage";
    }

    @GetMapping("userPostPage")
    public String userPostPage(Integer postId, Model model) {
        Post post = postService.getPost(postId);
        List<ReplyVo> replyList = replyService.getList(postId);
        model.addAttribute("post", post);
        model.addAttribute("replyList", replyList);
        return "userPostPage";
    }

    //个人中心自己的回复
    @GetMapping("userReplyPage")
    public String userReplyPage(Model model, Integer postId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Post post = postService.getPost(postId);

        model.addAttribute("post", post);
        List<Reply> userReplyList1 = replyService.getUserReplyList1(user.getUserId(), postId);
        model.addAttribute("list", userReplyList1);
        return "userReplyPage";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public String register(User user, @RequestParam("avatarImage") MultipartFile avatar) throws Exception {
        if (avatar != null) {

            String tmpDir = System.getProperty("user.home");
            String path = tmpDir + "/avatar";
            File avatarDir = new File(path);
            if (!avatarDir.exists()) {
                // 创建路径
                if (!avatarDir.mkdirs()) {
                    log.error("创建路径失败！");
                    throw new Exception("注册失败");
                }
            }
            //获取文件全名带后缀名
            String fileName = user.getUsername() + "-" + avatar.getOriginalFilename();
            avatar.transferTo(new File(path, fileName));
            //String realPath = session.getServletContext().getRealPath("/static/avatar/");

            System.out.println(path);
            user.setAvatar(fileName);


            try {
                this.userService.insert(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "redirect:/login";
    }


}
