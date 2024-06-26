package com.yandemelo.dscommercePGAdmin.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
        .requestMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**", "/v3/api-docs.yaml/**").permitAll()
        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
        .requestMatchers(HttpMethod.GET, "/users/me").hasAnyRole("ADMIN", "CLIENT")
        .requestMatchers(HttpMethod.GET, "/products").permitAll()
        .requestMatchers(HttpMethod.GET, "/products/{id}").permitAll()
        .requestMatchers(HttpMethod.GET, "/categories").permitAll()
        .requestMatchers(HttpMethod.GET, "/orders/{id}").hasAnyRole("ADMIN", "CLIENT")
        .requestMatchers(HttpMethod.PUT, "/products/{id}").hasAnyRole("ADMIN")
        .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
        .requestMatchers(HttpMethod.POST, "/orders").hasRole("CLIENT")
        .requestMatchers(HttpMethod.DELETE, "/products").hasRole("ADMIN")
        .anyRequest().authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}