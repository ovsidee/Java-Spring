package pl.edu.pja.s31719tpo04blog.controller;

import org.springframework.stereotype.Controller;
import pl.edu.pja.s31719tpo04blog.services.ArticleService;
import pl.edu.pja.s31719tpo04blog.services.BlogService;
import pl.edu.pja.s31719tpo04blog.services.RoleService;
import pl.edu.pja.s31719tpo04blog.services.UserService;

@Controller
public class ApplicationController {
    ArticleService articleService;
    BlogService blogService;
    UserService userService;
    RoleService roleService;

    public ApplicationController(ArticleService articleService, BlogService blogService, UserService userService, RoleService roleService) {
        this.articleService = articleService;
        this.blogService = blogService;
        this.userService = userService;
        this.roleService = roleService;
    }

   public void runController(){

   }
}