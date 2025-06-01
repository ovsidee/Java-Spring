package pl.edu.pja.tpo12.Config;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

@Component
public class RandomPasswordEncoder implements PasswordEncoder {

    private final Map<String, PasswordEncoder> encoders = Map.of(
            "bcrypt", new BCryptPasswordEncoder(),
            "argon2", new Argon2PasswordEncoder(16, 32, 1, 1024, 2),
            "scrypt", new SCryptPasswordEncoder(16384, 8, 1, 32, 64),
            "md5", new MessageDigestPasswordEncoder("MD5")
    );
    private final List<String> ids = List.copyOf(encoders.keySet());
    private final SecureRandom rnd = new SecureRandom();

    @Override
    public String encode(CharSequence rawPassword) {
        String id = ids.get(rnd.nextInt(ids.size()));
        return "{" + id + "}" + encoders.get(id).encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        int i = encodedPassword.indexOf('}');
        String id = encodedPassword.substring(1, i);
        return encoders.get(id).matches(rawPassword, encodedPassword.substring(i + 1));
    }
}
