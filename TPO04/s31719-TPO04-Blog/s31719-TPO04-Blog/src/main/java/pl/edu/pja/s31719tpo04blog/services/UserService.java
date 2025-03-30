package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataUserRepository;
import pl.edu.pja.s31719tpo04blog.tables.User;
import java.util.Optional;

@Service
public class UserService {
    private final SpringDataUserRepository userRepository;

    public UserService(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
