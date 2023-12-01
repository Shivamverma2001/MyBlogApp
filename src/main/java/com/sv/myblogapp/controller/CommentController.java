package com.sv.myblogapp.controller;

import com.sv.myblogapp.entity.Comment;
import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.service.CommentService;
import com.sv.myblogapp.service.PostService;
import com.sv.myblogapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    PostService postService;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;
    public CommentController(PostService postService,TagService tagService,CommentService commentService){
        this.postService=postService;
        this.tagService=tagService;
        this.commentService=commentService;
    }
    @GetMapping("/newComment/{id}")
    public String newComment
            (@PathVariable Integer id,@RequestParam("commenterName") String commenterName,@RequestParam("email")
            String email,@RequestParam("message") String message,@ModelAttribute("post") Post post){
        Post currentPost=postService.findById(id);

        Comment comment=new Comment(commenterName,email,message);
        comment.setPost(currentPost);
        currentPost.addComment(comment);

        postService.updateById(currentPost);
        return "redirect:/post/{id}";
    }
    @GetMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Integer id, Model model){
        commentService.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/editComment/{id}")
    public String editComment(@PathVariable Integer id,Model model){
        Comment comment=commentService.findCommentById(id);
        model.addAttribute("comment",comment);
        return "editComment";
    }
    @PostMapping("/editCommentProcess/{id}")
    public String editCommentProcess(@PathVariable Integer id,Model model,@ModelAttribute("comment") String updatedComment){
        System.out.println(updatedComment);
        System.out.println("----------------------------------------------------------------------------------------------------------");
        Comment comment=commentService.findCommentById(id);
        comment.setId(id);
        comment.setComment(updatedComment);

        commentService.add(comment);
        return "redirect:/";
    }
}
