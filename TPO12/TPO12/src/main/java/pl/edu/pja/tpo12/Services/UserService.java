package pl.edu.pja.tpo12.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.tpo12.Config.RandomPasswordEncoder;
import pl.edu.pja.tpo12.Models.AppUser;
import pl.edu.pja.tpo12.Models.DTO.UserDTO;
import pl.edu.pja.tpo12.Models.UserRole;
import pl.edu.pja.tpo12.Repositories.UserRepository;
import pl.edu.pja.tpo12.Repositories.UserRoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final RandomPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository roleRepository, RandomPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDTO> findUserCredentialsByEmail(String email) {
        return userRepository.findByEmail(email).map(UserDTOMapper::map);
    }

    @Transactional
    public void registerReader(String email, String rawPwd,
                               String firstName, String lastName) {

        AppUser u = new AppUser();
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(rawPwd));
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setRoles(Set.of(roleRepository.findByName("READER").orElseThrow()));

        userRepository.save(u);
    }

    @Transactional
    public void registerPublisher(String email, String rawPwd,
                                  String firstName, String lastName) {

        AppUser u = new AppUser();
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(rawPwd));
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setRoles(Set.of(roleRepository.findByName("PUBLISHER").orElseThrow()));

        userRepository.save(u);
    }

    public List<AppUser> getAllUsers() {
        return (List<AppUser>) userRepository.findAll();
    }

    @Transactional
    public void updateRoles(Long userId, Set<String> roleNames) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Set<UserRole> roles = roleNames.stream()
                .map(name -> roleRepository.findByName(name)
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + name)))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public AppUser getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void resetPassword(Long userId, String newPassword) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Transactional
    public void registerLibrarian(String email, String rawPwd,
                                  String firstName, String lastName) {

        AppUser u = new AppUser();
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(rawPwd));
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setRoles(Set.of(roleRepository.findByName("LIBRARIAN").orElseThrow()));

        userRepository.save(u);
    }

}