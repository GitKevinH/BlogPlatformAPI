package com.example.blogplatformapi.controller;


import com.example.blogplatformapi.model.BlogPost;
import com.example.blogplatformapi.service.BlogPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping
    public String getAllBlogPosts(Model model) {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
        model.addAttribute("blogPosts", blogPosts);
        model.addAttribute("blogPost", new BlogPost());
        return "blogposts";
    }

    @PostMapping
    public String createBlogPost(@ModelAttribute BlogPost blogPost) {
        blogPostService.createBlogPost(blogPost);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String getBlogPostById(@PathVariable Long id, Model model) {
        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        blogPost.ifPresent(post -> model.addAttribute("post", post));
        return "blogpost";
    }

    @GetMapping("/{id}/edit")
    public String editBlogPost(@PathVariable Long id, Model model) {
        Optional<BlogPost> blogPost = blogPostService.getBlogPostById(id);
        blogPost.ifPresent(post -> model.addAttribute("blogPost", post));
        return "blogposts";
    }

    @GetMapping("/{id}/delete")
    public String deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
        return "redirect:/posts";
    }
}
