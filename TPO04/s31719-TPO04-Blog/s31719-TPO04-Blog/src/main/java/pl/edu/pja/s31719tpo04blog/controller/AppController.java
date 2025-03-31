package pl.edu.pja.s31719tpo04blog.controller;

import org.springframework.stereotype.Controller;

import pl.edu.pja.s31719tpo04blog.services.ArticleService;
import pl.edu.pja.s31719tpo04blog.services.BlogService;
import pl.edu.pja.s31719tpo04blog.services.RoleService;
import pl.edu.pja.s31719tpo04blog.services.UserService;
import pl.edu.pja.s31719tpo04blog.tables.Article;
import pl.edu.pja.s31719tpo04blog.tables.Blog;
import pl.edu.pja.s31719tpo04blog.tables.Role;
import pl.edu.pja.s31719tpo04blog.tables.User;

import java.util.Scanner;

@Controller
public class AppController {
    public Scanner scanner;
    public ArticleService articleService;
    public BlogService blogService;
    public RoleService roleService;
    public UserService userService;

    public AppController(Scanner scanner, ArticleService articleService, BlogService blogService, RoleService roleService, UserService userService) {
        this.scanner = scanner;
        this.articleService = articleService;
        this.blogService = blogService;
        this.roleService = roleService;
        this.userService = userService;
    }

    public void runContoller() {
        while (true) {
            System.out.println(
                    "\nMain menu:" +
                            "\nType \"1\" to manage Articles\n" +
                            "Type \"2\" to manage Blogs.\n" +
                            "Type \"3\" to manage Roles\n" +
                            "Type \"4\" to manage Users\n" +
                            "Type \"5\" to exit."
            );

            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim();

            switch (command) {
                case "1" -> managingArticles();
                case "2" -> managingBlogs();
                case "3" -> managingRoles();
                case "4" -> managingUsers();
                case "5" -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid command! Try again.");
            }
        }
    }

    public void managingArticles() {
        System.out.println("You are now in Article Management mode.");

        System.out.println(
                "\nType \"1\" to add Article\n" +
                        "Type \"2\" to delete Article\n" +
                        "Type \"3\" to get all Articles\n" +
                        "Type \"4\" to search Articles\n" +
                        "Type \"5\" to find Article by id."
        );

        System.out.print("Enter command: ");
        String command = scanner.nextLine().trim();

        switch (command) {
            case "1" -> {
                System.out.print("Enter article title: ");
                String title = scanner.nextLine();

                System.out.print("Enter author ID: ");
                Long authorId = Long.parseLong(scanner.nextLine());

                System.out.print("Enter blog ID: ");
                Long blogId = Long.parseLong(scanner.nextLine());
                userService.findUserById(authorId).ifPresentOrElse(author ->
                                blogService.findBlogById(blogId)
                                        .ifPresentOrElse(blog -> {
                                            Article articleNew = new Article(title, author, blog);
                                            articleService.addArticle(articleNew);
                                            System.out.println("Article added!");
                                        }, () -> System.out.println("Invalid blog ID.")),
                        () -> System.out.println("Invalid author ID.")
                );
            }
            case "2" -> {
                System.out.print("Enter article ID to delete: ");
                Long id = Long.parseLong(scanner.nextLine());
                articleService.deleteArticle(id);
                System.out.println("Article deleted!");
            }
            case "3" -> {
                for (Article a : articleService.getAllArticles())
                    System.out.println(a);
            }
            case "4" -> {
                System.out.print("Enter title to search: ");
                String title = scanner.nextLine();
                articleService.searchArticles(title).forEach(System.out::println);
            }
            case "5" -> {
                System.out.print("Enter article ID: ");
                Long id = Long.parseLong(scanner.nextLine());
                articleService.findArticleById(id)
                        .ifPresentOrElse(
                                System.out::println,
                                () -> System.out.println("Article not found.")
                        );
            }
            default -> System.out.println("Invalid command! Try again.");
        }

    }

    public void managingBlogs() {
        System.out.println("You are now in Blog Management mode.");

        System.out.println(
                "\nType \"1\" to add Blog\n" +
                        "Type \"2\" to delete Blog\n" +
                        "Type \"3\" to get all Blogs\n" +
                        "Type \"4\" to search Blog\n" +
                        "Type \"5\" to find Blog by id."
        );
        System.out.print("Enter command: ");
        String command = scanner.nextLine().trim();

        switch (command) {
            case "1" -> {
                System.out.print("Enter blog name: ");
                String name = scanner.nextLine();
                System.out.print("Enter manager ID: ");
                Long managerId = Long.parseLong(scanner.nextLine());

                userService.findUserById(managerId).ifPresentOrElse(manager -> {
                    blogService.addBlog(new Blog(name, manager));
                    System.out.println("Blog added!");
                }, () -> System.out.println("Invalid manager ID."));
            }
            case "2" -> {
                System.out.print("Enter blog ID to delete: ");
                Long id = Long.parseLong(scanner.nextLine());
                blogService.deleteBlog(id);
                System.out.println("Blog deleted!");
            }
            case "3" -> {
                for (Blog b : blogService.getAllBlogs())
                    System.out.println(b);
            }
            case "4" -> {
                System.out.print("Enter blog name to search: ");
                String name = scanner.nextLine();
                blogService.searchBlogs(name).forEach(System.out::println);
            }
            case "5" -> {
                System.out.print("Enter blog ID: ");
                Long id = Long.parseLong(scanner.nextLine());
                blogService.findBlogById(id)
                        .ifPresentOrElse(
                                System.out::println,
                                () -> System.out.println("Blog not found.")
                        );
            }
            default -> System.out.println("Invalid command! Try again.");
        }

    }

    public void managingRoles() {
        System.out.println("You are now in Role Management mode.");

        System.out.println(
                "\nType \"1\" to add Role\n" +
                        "Type \"2\" to delete Role\n" +
                        "Type \"3\" to get all Roles\n" +
                        "Type \"4\" to search Role\n" +
                        "Type \"5\" to find Role by id."
        );
        System.out.print("Enter command: ");
        String command = scanner.nextLine().trim();

        switch (command) {
            case "1" -> {
                System.out.print("Enter role name: ");
                String name = scanner.nextLine();
                roleService.addRole(new Role(name));
                System.out.println("Role added!");
            }
            case "2" -> {
                System.out.print("Enter role ID to delete: ");
                Long id = Long.parseLong(scanner.nextLine());
                roleService.deleteRole(id);
                System.out.println("Role deleted!");
            }
            case "3" -> {
                for (Role r : roleService.getAllRoles())
                    System.out.println(r);
            }
            case "4" -> {
                System.out.print("Enter role name to search: ");
                String name = scanner.nextLine();
                roleService.searchRoles(name).forEach(System.out::println);
            }
            case "5" -> {
                System.out.print("Enter role ID: ");
                Long id = Long.parseLong(scanner.nextLine());
                roleService.findRoleById(id)
                        .ifPresentOrElse(
                                System.out::println,
                                () -> System.out.println("Role not found.")
                        );
            }
            default -> System.out.println("Invalid command! Try again.");
        }

    }

    public void managingUsers() {
        System.out.println("You are now in User Management mode.");

        System.out.println(
                "\nType \"1\" to add User\n" +
                        "Type \"2\" to delete User\n" +
                        "Type \"3\" to get all Users\n" +
                        "Type \"4\" to search User by email\n" +
                        "Type \"5\" to search User by Blog ID\n" +
                        "Type \"6\" to find User by id."
        );
        System.out.print("Enter command: ");
        String command = scanner.nextLine().trim();

        switch (command) {
            case "1" -> {
                System.out.print("Enter user email: ");
                String email = scanner.nextLine();
                userService.addUser(new User(email));
                System.out.println("User added!");
            }
            case "2" -> {
                System.out.print("Enter user ID to delete: ");
                Long id = Long.parseLong(scanner.nextLine());
                userService.deleteUser(id);
                System.out.println("User deleted!");
            }
            case "3" -> {
                for (User u : userService.getAllUsers())
                    System.out.println(u);
            }
            case "4" -> {
                System.out.print("Enter email to search: ");
                String email = scanner.nextLine();
                userService.searchUsers(email).forEach(System.out::println);
            }
            case "5" -> {
                System.out.println("Enter blog ID to search: ");
                Long id = Long.parseLong(scanner.nextLine());
                userService.findUserById(id)
                        .ifPresentOrElse(
                            System.out::println,
                                () -> System.out.println("User not found.")
                        );
            }
            case "6" -> {
                System.out.print("Enter user ID: ");
                Long id = Long.parseLong(scanner.nextLine());
                for(User u : userService.findUsersByBlogId(id))
                    System.out.println(u);
            }
            default -> System.out.println("Invalid command! Try again.");
        }

    }
}
