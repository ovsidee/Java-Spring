package pl.edu.pja.s31719tpo04blog.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.edu.pja.s31719tpo04blog.tables.User;

import java.util.List;

public interface SpringDataUserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u")
    List<User> findAllUsers();

    @Query("SELECT u FROM User u" +
            " WHERE LOWER(u.email)" +
            " LIKE LOWER(CONCAT('%', :email, '%'))")
    List<User> findByEmailContainingIgnoreCase(String email);

    @Query("SELECT DISTINCT a.author FROM Article a " +
            "WHERE a.blog.id = :blogId")
    List<User> findUsersByBlogId(@Param("blogId") Long blogId);

    @Query("SELECT u FROM User u " +
            "JOIN u.roles r" +
            " WHERE r.name = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);

    @Query("SELECT b.manager FROM Blog b" +
            " WHERE b.id = :blogId")
    User findManagerByBlogId(@Param("blogId") Long blogId);

    @Query("SELECT u FROM User u" +
            " JOIN u.articles a " +
            "GROUP BY u " +
            "HAVING COUNT(a) >= :minArticles")
    List<User> findUsersByMinArticles(@Param("minArticles") Long minArticles);

    @Query("SELECT u FROM User u" +
            " LEFT JOIN u.articles a" +
            " WHERE a IS NULL")
    List<User> findUsersWithoutArticles();

}
