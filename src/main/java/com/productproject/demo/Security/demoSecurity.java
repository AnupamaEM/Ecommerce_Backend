package com.productproject.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class demoSecurity {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select peoplename,password,enabled from peoples where peoplename=?");
        
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select peoplename, authority from authorities where peoplename=?");
        
        return jdbcUserDetailsManager;   
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(configurer -> configurer
                // Role-based access control
                .requestMatchers(HttpMethod.POST, "/store/**").hasAnyRole("USER", "MANAGER") 
                .requestMatchers(HttpMethod.PUT, "/store/**").hasRole("MANAGER") 
                .requestMatchers(HttpMethod.GET, "/store/user/**").hasAnyRole("USER", "MANAGER", "ADMIN") 
                .requestMatchers(HttpMethod.DELETE, "/store/**").hasRole("ADMIN") 
                // .requestMatchers(HttpMethod.GET, "/store/user/**").hasAnyRole("ADMIN", "USER", "MANAGER") 
                .anyRequest().authenticated() 
            );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build(); 
    }
}
