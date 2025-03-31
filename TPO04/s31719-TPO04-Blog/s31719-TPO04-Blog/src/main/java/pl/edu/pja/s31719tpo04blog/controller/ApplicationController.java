//package pl.edu.pja.s31719tpo04blog.controller;
//
//import org.springframework.stereotype.Controller;
//import pl.edu.pja.s31719tpo04blog.services.ArticleService;
//import pl.edu.pja.s31719tpo04blog.services.BlogService;
//import pl.edu.pja.s31719tpo04blog.services.RoleService;
//import pl.edu.pja.s31719tpo04blog.services.UserService;
//import pl.edu.pja.s31719tpo04blog.tables.*;
//
//import java.util.Scanner;
//
//@Controller
//public class ApplicationController {
//    public Scanner scanner;
//    public ArticleService articleService;
//    public BlogService blogService;
//    public UserService userService;
//    public RoleService roleService;
//
//    public ApplicationController(Scanner scanner, ArticleService articleService, BlogService blogService, UserService userService, RoleService roleService) {
//        this.scanner = scanner;
//        this.articleService = articleService;
//        this.blogService = blogService;
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    public void runController() {
//        while (true) {
//            System.out.println("\n--- MENU ---");
//            System.out.println("1. Manage Articles");
//            System.out.println("2. Manage Blogs");
//            System.out.println("3. Manage Users");
//            System.out.println("4. Manage Roles");
//            System.out.println("5. Exit");
//            System.out.print("Enter choice: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1 -> manageArticles();
//                case 2 -> manageBlogs();
//                case 3 -> manageUsers();
//                case 4 -> manageRoles();
//                case 5 -> {
//                    System.out.println("Exiting...");
//                    return;
//                }
//                default -> System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }
//
//    public void manageArticles() {
//        System.out.println("\n--- ARTICLE MANAGEMENT ---");
//        System.out.println("1. View Articles");
//        System.out.println("2. Add Article");
//        System.out.println("3. Search Article");
//        System.out.println("4. Delete Article");
//        System.out.print("Enter choice: ");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1 -> articleService.getAllArticles().forEach(System.out::println);
//            case 2 -> {
//                System.out.print("Enter title: ");
//                String title = scanner.nextLine();
//                articleService.addArticle(new Article(title, null, null));
//                System.out.println("Article added.");
//            }
//            case 3 -> {
//                System.out.print("Enter search term: ");
//                String searchTerm = scanner.nextLine();
//                articleService.searchArticles(searchTerm).forEach(System.out::println);
//            }
//            case 4 -> {
//                System.out.print("Enter article ID to delete: ");
//                Long id = scanner.nextLong();
//                articleService.deleteArticle(id);
//                System.out.println("Article deleted.");
//            }
//        }
//    }
//    public void manageBlogs() {
//        System.out.println("\n--- BLOG MANAGEMENT ---");
//        System.out.println("1. View Blogs");
//        System.out.println("2. Add Blog");
//        System.out.println("3. Delete Blog");
//        System.out.print("Enter choice: ");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1 -> blogService.getAllBlogs().forEach(System.out::println);
//            case 2 -> {
//                System.out.print("Enter blog name: ");
//                String name = scanner.nextLine();
//                blogService.addBlog(new Blog(name, null));
//                System.out.println("Blog added.");
//            }
//            case 3 ->{
//                System.out.print("Enter blog ID to delete: ");
//                Long id = scanner.nextLong();
//                blogService.deleteBlog(id);
//                System.out.println("Blog deleted.");
//            }
//            default -> System.out.println("Invalid choice. Try again.");
//        }
//    }
//    public void manageUsers() {
//        System.out.println("\n--- USER MANAGEMENT ---");
//        System.out.println("1. View Users");
//        System.out.println("2. Add User");
//        System.out.println("3. Delete User");
//        System.out.print("Enter choice: ");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1 -> userService.getAllUsers().forEach(System.out::println);
//            case 2 -> {
//                System.out.print("Enter user email: ");
//                String email = scanner.nextLine();
//                userService.addUser(new User(email));
//                System.out.println("User added.");
//            }
//            case 3 -> {
//                System.out.print("Enter user ID to delete: ");
//                Long id = scanner.nextLong();
//                userService.deleteUser(id);
//                System.out.println("User deleted.");
//            }
//            default -> System.out.println("Invalid choice. Try again.");
//        }
//    }
//    public void manageRoles() {
//        System.out.println("\n--- ROLE MANAGEMENT ---");
//        System.out.println("1. View Roles");
//        System.out.println("2. Add Role");
//        System.out.println("3. Delete Role");
//        System.out.print("Enter choice: ");
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (choice) {
//            case 1 -> roleService.getAllRoles().forEach(System.out::println);
//            case 2 -> {
//                System.out.print("Enter role name: ");
//                String name = scanner.nextLine();
//                roleService.addRole(new Role(name));
//                System.out.println("Role added.");
//            }
//            case 3 -> {
//                System.out.print("Enter role ID to delete: ");
//                Long id = scanner.nextLong();
//                roleService.deleteRole(id);
//                System.out.println("Role deleted.");
//            }
//            default -> System.out.println("Invalid choice. Try again.");
//        }
//    }
//}
