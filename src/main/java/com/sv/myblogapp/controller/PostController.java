package com.sv.myblogapp.controller;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }
    @RequestMapping("/")
    public String blogPostLandingPage(Model model){
        model.addAttribute("posts",postService.findAll());
        return "home";
    }
    @RequestMapping("/newpost")
    public String createPost(Model model){
        Post post=new Post();
        model.addAttribute("post",post);
        return "createPost";
    }
    @PostMapping("/newpostprocess")
    public String show(@ModelAttribute("post") Post post){
        postService.addPost(post);
        return "createPost";
    }
    @GetMapping("/post{id}")
    public String show(@PathVariable Integer id, Model model){
        List<Post> posts=postService.findAllById((int)id);
        model.addAttribute("post",posts.get(0));
        return "show";
    }
    @GetMapping("/delete{id}")
    public String delete(@PathVariable Integer id,Model model){
        postService.deleteById((int)id);
        return "redirect:/";
    }
}
