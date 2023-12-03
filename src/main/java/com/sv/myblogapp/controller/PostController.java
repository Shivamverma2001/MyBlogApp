package com.sv.myblogapp.controller;

import com.sv.myblogapp.entity.Comment;
import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.entity.Tag;
import com.sv.myblogapp.service.PostService;
import com.sv.myblogapp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        return findPaginated(1,"title","asc",model);
    }
    @RequestMapping("/newpost")
    public String createPost(Model model){
        Post post=new Post();
        Tag tag=new Tag();
        model.addAttribute("post",post);
        model.addAttribute("tag",tag);
        return "createPost";
    }
    @PostMapping("/newpostprocess")
    public String show(@ModelAttribute("post") Post post,@ModelAttribute("tag") Tag newTag){
        Map<String,Tag> tempTags=new HashMap<>();
        List<Tag> allTags=tagService.findAllTags();

        for(Tag tag:allTags){
            String name= tag.getName();
            tempTags.put(name,tag);
        }
        String[] tagsArray = newTag.getName().split(",");
        for(String tempTag: tagsArray){
            if(tempTags.containsKey(tempTag)){
                post.addTag(tempTags.get(tempTag));
            }
            else {
                tempTag = tempTag.trim();
                Tag tag = new Tag(tempTag);
                post.addTag(tag);
            }
        }

        postService.addPost(post);
        return "redirect:/";
    }
    @GetMapping("/post/{id}")
    public String show(@PathVariable Integer id, Model model){
        List<Post> posts=postService.findAllById((int)id);

        Set<Tag> tags = posts.get(0).getTags();
        List<Comment> comments=posts.get(0).getComments();
        System.out.println(tags);
        model.addAttribute("post",posts.get(0));
        model.addAttribute("tags",tags);
        model.addAttribute("comments",comments);
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
        Set<Tag> tags=posts.get(0).getTags();
        String commenterName = null;
        String email=null;
        String message=null;
        System.out.println(tags);
        StringBuilder tagName= new StringBuilder("");
        for(Tag tag:tags){
            tagName.append(tag.getName()).append(",");
        }
        model.addAttribute("post",posts.get(0));
        model.addAttribute("tagName",tagName);
        return "updatePost";
    }
    @RequestMapping("/editPost/{id}")
    public String editPost
            (@PathVariable("id") Integer id,
             @ModelAttribute("post") Post updatedPost,@RequestParam("tagName") String updatedTagName){
        System.out.println(updatedTagName);
        List<Post> posts=postService.findAllById((int)id);
        Post currentPost=posts.get(0);

        currentPost.setId(updatedPost.getId());
        currentPost.setAuthor(updatedPost.getAuthor());
        currentPost.setTitle(updatedPost.getTitle());
        currentPost.setContent(updatedPost.getContent());

        Set<Tag> tags=currentPost.getTags();
        Set<Tag> newTags=new HashSet<>();
        String []tagsName=updatedTagName.split(",");

        for (String tagName:tagsName){
            if (!tags.contains(tagName)){
                Tag newTag=new Tag(tagName);
                newTags.add(newTag);
            }
        }
        currentPost.setTags(newTags);

        postService.updateById(currentPost);

        return "redirect:/";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated
            (@PathVariable(value="pageNo") int pageNo,@RequestParam("sortField") String sortField,
             @RequestParam("sortDir") String sortDir, Model model){
        int pageSize=4;
        Page<Post> page=postService.findPaginated(pageNo,pageSize,sortField,sortDir);

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("posts",page.getContent());
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("tags", null);
        return "home";
    }
    @GetMapping("/searchByTags/{pageNo}")
    public String searchByTags(
            @RequestParam(name = "tags", required = false) String tags,
            @PathVariable(value = "pageNo") int pageNo,
            @RequestParam(name = "sortField", defaultValue = "publishedAt") String sortField,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
            Model model) {
        int pageSize = 4;
        Page<Post> page = postService.searchByTagsPaginated(tags, pageNo, pageSize, sortField, sortDir);
        System.out.println(page);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("posts", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        return "home";
    }
    @GetMapping("/searchByString/{pageNo}")
    public String searchByString(
            @RequestParam(name = "searchString",required = false) String searchString,
            @RequestParam(name = "tags", required = false) String tags,
            @PathVariable(value = "pageNo") int pageNo,
            @RequestParam(name = "sortField", defaultValue = "publishedAt") String sortField,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
            Model model) {
        int pageSize = 4;
        Page<Post> page = postService.searchByStringPaginated(searchString, pageNo, pageSize, sortField, sortDir);
        System.out.println(page);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("posts", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        return "home";
    }
}
