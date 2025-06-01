package pl.edu.pja.tpo12.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService uds;
    private final RandomPasswordEncoder randomPasswordEncoder;

    public SecurityConfig(UserDetailsService uds, RandomPasswordEncoder randomPasswordEncoder) {
        this.uds = uds;
        this.randomPasswordEncoder = randomPasswordEncoder;
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(uds).passwordEncoder(randomPasswordEncoder);
        return builder.build();
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        var rh = new RoleHierarchyImpl();
        rh.setHierarchy("""
            ROLE_ADMIN > ROLE_LIBRARIAN
            ROLE_LIBRARIAN > ROLE_PUBLISHER
            ROLE_PUBLISHER > ROLE_READER
        """);
        return rh;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webExpr(RoleHierarchyImpl rh) {
        var h = new DefaultWebSecurityExpressionHandler();
        h.setRoleHierarchy(rh);
        return h;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                  .requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
                  .requestMatchers("/", "/register/**", "/api/books/**").permitAll()
                  .requestMatchers("/reader/**").hasRole("READER")
                  .requestMatchers("/publisher/**").hasRole("PUBLISHER")
                  .requestMatchers("/librarian/**").hasRole("LIBRARIAN")
                  .requestMatchers("/admin/**").hasRole("ADMIN")
                  .anyRequest().authenticated()
                ).formLogin(form -> form.loginPage("/login").permitAll())
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchyImpl rh) {
        var handler = new DefaultMethodSecurityExpressionHandler();
        handler.setRoleHierarchy(rh);
        return handler;
    }
}