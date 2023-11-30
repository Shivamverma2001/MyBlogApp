package com.sv.myblogapp.controller;

import com.sv.myblogapp.entity.Comment;
import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.service.CommentService;
import com.sv.myblogapp.service.PostService;
import com.sv.myblogapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
}
