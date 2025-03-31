package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataUserRepository;
import pl.edu.pja.s31719tpo04blog.tables.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final SpringDataUserRepository userRepository;

    public UserService(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> searchUsers(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    public List<User> findUsersByBlogId(Long blogId) {
        return userRepository.findUsersByBlogId(blogId);
    }

    public List<User> findUsersByRoleName(String roleName) {
        return userRepository.findByRoleName(roleName);
    }

    public User findUserManagerByBlogId(Long blogId) {
        return userRepository.findManagerByBlogId(blogId);
    }

    public List<User> findAllUsersByMinArticles(Long minArticles) {
        return userRepository.findUsersByMinArticles(minArticles);
    }
    
    public List<User> findAllUsersWithoutArticles() {
        return userRepository.findUsersWithoutArticles();
    }
}
