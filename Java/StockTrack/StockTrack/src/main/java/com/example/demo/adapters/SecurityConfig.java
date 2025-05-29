package com.example.demo.adapters;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/login", "/register","/css/**", "/js/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")  // Only ADMIN can access /admin/*
                    .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // USER or ADMIN can access /user/*
                    .anyRequest().authenticated()  // All other requests require authentication
                    .and()
                .formLogin()  // Enable form-based login
                    .loginPage("/login")  // Custom login page
                    .defaultSuccessUrl("/mainscreen", true)
                    .permitAll()
                    .and()
                .logout()  // Enable logout
                    .logoutUrl("/logout") // URL for logout (can be customized)
                    .logoutSuccessUrl("/login?logout") // Redirect to login page with a logout message
                    .permitAll();
    }

    // In-memory user store (replace with database-backed user details later)
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password(passwordEncoder().encode("admin123"))
                        .roles("ADMIN")
                        .build(),
                User.withUsername("user")
                        .password(passwordEncoder().encode("user123"))
                        .roles("USER")
                        .build()
        );
    }

    // Password encoder (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}