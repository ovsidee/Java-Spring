package pl.edu.pja.s31719tpo04blog.services;

import org.springframework.stereotype.Service;
import pl.edu.pja.s31719tpo04blog.repositories.SpringDataUserRepository;

@Service
public class UserService {
    SpringDataUserRepository repository;

    public UserService(SpringDataUserRepository repository) {
        this.repository = repository;
    }

}
