package com.example.flowershop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.flowershop.repositories.RoleRepository;
import com.example.flowershop.repositories.UserRepository;
import com.example.flowershop.services.UserService;
import com.example.flowershop.services.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static UserService userDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        return new UserServiceImpl(userRepository, roleRepository, passwordEncoder());
    }

    @Bean
    public static AuthenticationProvider authenticationProvider(UserRepository userRepository, RoleRepository roleRepository) {
        var provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService(userRepository, roleRepository));
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public static SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/account", "/shoppingCart").hasAuthority("ROLE_USER")
                        .requestMatchers("/admin", "/admin/*").hasAuthority("ROLE_ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/account")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));
        return http.build();
    }
}
