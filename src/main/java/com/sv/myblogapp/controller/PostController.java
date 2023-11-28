package com.sv.myblogapp.controller;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.entity.Tag;
import com.sv.myblogapp.service.PostService;
import com.sv.myblogapp.service.TagService;
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
    @Autowired
    TagService tagService;

    public PostController(PostService postService,TagService tagService){
        this.postService=postService;
        this.tagService=tagService;
    }
    @RequestMapping("/")
    public String blogPostLandingPage(Model model){
        model.addAttribute("posts",postService.findAll());
        model.addAttribute("tags",tagService.findAllTags());
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

        List<String> tags=  post.getTags();

        for(String tagName:tags){
            Tag tag = new Tag();
            tag.setName(tagName);
            tagService.addTag(tag);
        }

        return "createPost";
    }
    @GetMapping("/post/{id}")
    public String show(@PathVariable Integer id, Model model){
        List<Post> posts=postService.findAllById((int)id);
        model.addAttribute("post",posts.get(0));
        return "show";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,Model model){
        postService.deleteById((int)id);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        List<Post> posts=postService.findAllById((int)id);
        model.addAttribute("post",posts.get(0));
        return "updatePost";
    }
    @RequestMapping("/editPost/{id}")
    public String editPost(@PathVariable("id") Integer id,@ModelAttribute("post") Post updatedPost){
        List<Post> posts=postService.findAllById((int)id);
        Post currentPost=posts.get(0);

        currentPost.setId(updatedPost.getId());
        currentPost.setAuthor(updatedPost.getAuthor());
        currentPost.setTitle(updatedPost.getTitle());
        currentPost.setContent(updatedPost.getContent());

        postService.updateById(currentPost);
        return "redirect:/";
    }
}
