package com.example.blog;

import com.example.blog.domain.BlogPost;
import com.example.blog.domain.BlogService;
import com.example.blog.domain.BlogUserDetailsService;
import com.example.blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BlogController {
    private final BlogService blogService;
    private final BlogUserDetailsService blogUserDetailsService;

    @Autowired
    public BlogController(BlogService blogService, BlogUserDetailsService blogUserDetailsService) {
        this.blogService = blogService;
        this.blogUserDetailsService = blogUserDetailsService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("blogPosts", this.getBlogPosts());
        model.addAttribute("authors", blogUserDetailsService.getAll());
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
    @RequestMapping(value = "/byusername")
    public String showbyUsername(@RequestParam(value = "username") String username, Model model) {
        User user = blogUserDetailsService.getOne(username);

        List<BlogPost> blogPosts = blogService.getAll(user);
        model.addAttribute("blogPosts", blogPosts);
        model.addAttribute("authors", blogUserDetailsService.getAll());
        return "index";
    }
    @RequestMapping(value = "/search")
    public String search(@RequestParam(value = "search_query") String searchQuery, Model model) {

        List<BlogPost> searched = getBlogPosts().stream()
                .filter(blogPost
                        -> blogPost.getAuthor().getFullName().contains(searchQuery)
                        || blogPost.getAuthor().getUsername().contains(searchQuery))
                .collect(Collectors.toList());


        model.addAttribute("blogPosts", searched);
        model.addAttribute("authors", blogUserDetailsService.getAll());
        return "index";
    }

    private List<BlogPost> getBlogPosts() {
        return blogService.getAllPosts();
    }
}
