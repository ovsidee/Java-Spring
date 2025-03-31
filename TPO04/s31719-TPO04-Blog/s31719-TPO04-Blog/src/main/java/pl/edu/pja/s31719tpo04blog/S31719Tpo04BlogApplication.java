package pl.edu.pja.s31719tpo04blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.edu.pja.s31719tpo04blog.services.ArticleService;
import pl.edu.pja.s31719tpo04blog.services.BlogService;
import pl.edu.pja.s31719tpo04blog.services.RoleService;
import pl.edu.pja.s31719tpo04blog.services.UserService;
import pl.edu.pja.s31719tpo04blog.tables.Article;
import pl.edu.pja.s31719tpo04blog.tables.Blog;
import pl.edu.pja.s31719tpo04blog.tables.Role;
import pl.edu.pja.s31719tpo04blog.tables.User;

import java.util.Optional;

@SpringBootApplication
public class S31719Tpo04BlogApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(S31719Tpo04BlogApplication.class, args);

        ArticleService articleService = context.getBean(ArticleService.class);
        BlogService blogService = context.getBean(BlogService.class);
        UserService userService = context.getBean(UserService.class);
        RoleService roleService = context.getBean(RoleService.class);

        User user1 = new User("artem.shestak@gmail.com");
        userService.addUser(user1);

        Role role1 = new Role("IT-Specialist");
        roleService.addRole(role1);

        Blog blog1 = new Blog("Do not talk about Fight Club", user1);
        blogService.addBlog(blog1);

        Article article1 = new Article("Fight Club", user1, blog1);
        articleService.addArticle(article1);

        System.out.println("\nCreated Entities:");
        System.out.println(article1);
        System.out.println(blog1);
        System.out.println(user1);
        System.out.println(role1);

        System.out.println("\nRetrieving Entities: (with already existing data)");
        System.out.println("All Articles: " + articleService.getAllArticles());
        System.out.println("All Blogs: " + blogService.getAllBlogs());
        System.out.println("All Users: " + userService.getAllUsers());
        System.out.println("All Roles: " + roleService.getAllRoles());

        System.out.println("\nSearching for Articles with 'Spring':");
        articleService.searchArticles("Spring").forEach(System.out::println);

        System.out.println("\nDeleting User with ID: " + user1.getId());
        userService.deleteUser(user1.getId());

        System.out.println("Deleting User with ID: 10");
        userService.deleteUser(10L);

        System.out.println("All Users after Deletion: " + userService.getAllUsers());

        Optional<Article> fetchedArticle = articleService.findArticleById(article1.getId());
        fetchedArticle.ifPresentOrElse(
                article -> System.out.println("\nRetrieved Article: " + article),
                () -> System.out.println("\nArticle not found")
        );
    }

}
