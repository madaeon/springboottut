package com.example.blog;

import com.example.blog.domain.BlogPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class BlogController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("blogPosts", this.getBlogPosts());
        return "index";
    }

    private List<BlogPost> getBlogPosts() {
        BlogPost blogPost = new BlogPost("1", "erster BlogPost", "Mein erster BlogPost.");

        List<BlogPost> blogPosts = new LinkedList<>();
        blogPosts.add(blogPost);

        return blogPosts;
    }
}
