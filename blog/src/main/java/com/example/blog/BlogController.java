package com.example.blog;

import com.example.blog.domain.BlogPost;
import com.example.blog.domain.BlogService;
import com.example.blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("blogPosts", this.getBlogPosts());
        return "index";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showNewPostForm(NewBlogPostCmd newBlogPostCmd) {
        return "newPostForm";
    }
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveNewPost(NewBlogPostCmd newBlogPostCmd, @AuthenticationPrincipal User currentUser) {
        BlogPost blogPost = new BlogPost(null, currentUser, newBlogPostCmd.getTitle(), newBlogPostCmd.getText());
        blogService.createBlogPost(blogPost);
        return "redirect:/";
    }

    private List<BlogPost> getBlogPosts() {
        return blogService.getAllPosts();
    }
}
