package pl.edu.pja.tpo12.Authentification;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import pl.edu.pja.tpo12.Services.UserService;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService users;

    public CustomUserDetailsService(UserService users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("searching for a user...:\"" + email + "\"");
        return users.findUserCredentialsByEmail(email)
                .map(dto -> {
                    var authorities = dto.getRoles().stream()
                            .toArray(String[]::new);

                    System.out.println("Authorities: " + Arrays.toString(authorities));

                    return User.withUsername(dto.getEmail())
                            .password(dto.getPassword())
                            .authorities(authorities)
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User '%s' not found".formatted(email)));
    }
}