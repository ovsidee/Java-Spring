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
        System.out.print("\nYou are now in Article Management mode.");
        System.out.println(
                "\nType \"1\" to add Article\n" +
                        "Type \"2\" to delete Article\n" +
                        "Type \"3\" to get all Articles\n" +
                        "Type \"4\" to search Articles\n" +
                        "Type \"5\" to find Article by id\n" +
                        "Type \"6\" to find Articles by author ID\n" +
                        "Type \"7\" to find Articles by blog ID\n" +
                        "Type \"8\" to find Articles by author email\n" +
                        "Type \"9\" to search Articles by keyword."
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
            case "6" -> {
                System.out.print("Enter author ID: ");
                Long authorId = Long.parseLong(scanner.nextLine());
                articleService.findArticlesByAuthorId(authorId).forEach(System.out::println);
            }
            case "7" -> {
                System.out.print("Enter blog ID: ");
                Long blogId = Long.parseLong(scanner.nextLine());
                articleService.findArticlesByBlogId(blogId).forEach(System.out::println);
            }
            case "8" -> {
                System.out.print("Enter author email: ");
                String email = scanner.nextLine();
                articleService.findArticlesByAuthorEmail(email).forEach(System.out::println);
            }
            case "9" -> {
                System.out.print("Enter keyword: ");
                String keyword = scanner.nextLine();
                articleService.searchArticlesByKeyword(keyword).forEach(System.out::println);
            }
            default -> System.out.println("Invalid command! Try again.");
        }

    }

    public void managingBlogs() {
        System.out.println("\nYou are now in Blog Management mode.");
        System.out.println(
                "\nType \"1\" to add Blog\n" +
                        "Type \"2\" to delete Blog\n" +
                        "Type \"3\" to get all Blogs\n" +
                        "Type \"4\" to search Blog\n" +
                        "Type \"5\" to find Blog by id\n" +
                        "Type \"6\" to find Blog by manager ID\n" +
                        "Type \"7\" to find Blogs with minimum articles."
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
            case "6" -> {
                System.out.print("Enter manager ID: ");
                Long managerId = Long.parseLong(scanner.nextLine());
                System.out.println(blogService.findBlogByManagerId(managerId));
            }
            case "7" -> {
                System.out.println("Enter the minimum articles:");
                Long min = Long.parseLong(scanner.nextLine());
                blogService.findAllBlogsByMinArticles(min).forEach(System.out::println);
            }
            default -> System.out.println("Invalid command! Try again.");
        }

    }

    public void managingRoles() {
        System.out.println("\nYou are now in Role Management mode.");
        System.out.println(
                "\nType \"1\" to add Role\n" +
                        "Type \"2\" to delete Role\n" +
                        "Type \"3\" to get all Roles\n" +
                        "Type \"4\" to search Role\n" +
                        "Type \"5\" to find Role by id\n" +
                        "Type \"6\" to find roles without users\n" +
                        "Type \"7\" to find roles by minimum users."
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
            case "6" -> {
                for (Role r : roleService.findAllRolesWithoutUsers())
                    System.out.println(r);
            }
            case "7" -> {
                System.out.println("Enter minumum users:");
                Long min = Long.parseLong(scanner.nextLine());
                for(Role r: roleService.findAllRolesByMinUsers(min))
                    System.out.println(r);
            }
            default -> System.out.println("Invalid command! Try again.");
        }

    }
    public void managingUsers() {
        System.out.println("\nYou are now in User Management mode.");
        System.out.println(
                "\nType \"1\" to add User\n" +
                        "Type \"2\" to delete User\n" +
                        "Type \"3\" to get all Users\n" +
                        "Type \"4\" to search User by email\n" +
                        "Type \"5\" to find User by ID\n" +
                        "Type \"6\" to find Users by Blog id\n" +
                        "Type \"7\" to find Users by role name\n" +
                        "Type \"8\" to find User (manager) by Blog ID\n" +
                        "Type \"9\" to find Users with minimum articles\n" +
                        "Type \"10\" to find Users without articles."
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
                System.out.println("Enter User ID to search: ");
                Long id = Long.parseLong(scanner.nextLine());
                userService.findUserById(id)
                        .ifPresentOrElse(
                            System.out::println,
                                () -> System.out.println("User not found.")
                        );
            }
            case "6" -> {
                System.out.print("Enter Blog ID: ");
                Long id = Long.parseLong(scanner.nextLine());
                for(User u : userService.findUsersByBlogId(id))
                    System.out.println(u);
            }
            case "7" -> {
                System.out.print("Enter role name: ");
                String roleName = scanner.nextLine();
                userService.findUsersByRoleName(roleName).forEach(System.out::println);
            }
            case "8" -> {
                System.out.print("Enter blog ID: ");
                Long blogId = Long.parseLong(scanner.nextLine());
                System.out.println(userService.findUserManagerByBlogId(blogId));
            }
            case "9" -> {
                System.out.print("Enter minimum number of articles: ");
                Long minArticles = Long.parseLong(scanner.nextLine());
                userService.findAllUsersByMinArticles(minArticles).forEach(System.out::println);
            }
            case "10" -> userService.findAllUsersWithoutArticles().forEach(System.out::println);
            default -> System.out.println("Invalid command! Try again.");
        }

    }
}
