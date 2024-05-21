package com.example.TaskManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for testing with Postman
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/save").hasAnyRole("USER", "ADMIN") // Ensure this endpoint is accessible to specific roles
                        .requestMatchers("/**").permitAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails normalUser = User.builder()
                .username("user")
                .password("$2a$12$SAaLrLzzXX/ByzXv7A0PTeox/X4pIeMt5EgplLlnYpESy7I2fENU2")
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password("$2a$12$U52Vox6jQ7G7iOO..dA.5.QdPLjJLvSlDlTkEVebqIXl.80G4SMHa")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
