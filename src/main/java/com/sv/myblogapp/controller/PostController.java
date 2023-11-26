package com.sv.myblogapp.controller;

import com.sv.myblogapp.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    @RequestMapping("/")
    public String blogPostLandingPage(Model model){
        return "blog-post";
    }
    @RequestMapping("/newpost")
    public String createPost(){
        return "newPost";
    }
    @GetMapping("/newpostprocess")
    public void show(@RequestParam("title") String title){
        System.out.println(title);
    }
}
