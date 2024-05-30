package com.fabhotel.fabhotel.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class SecurityConfig {

    private JwtSecurityFilter jwtSecurityFilter;

    public SecurityConfig(JwtSecurityFilter jwtSecurityFilter) {
        this.jwtSecurityFilter = jwtSecurityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
      //  http.addFilterBefore(jwtSecurityFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests()
                //.requestMatchers("/property/**","/api/propertyUser/**","/")
               // .permitAll()
         .anyRequest().permitAll();//.authenticated();
        return http.build();
    }
    @Bean
    public PasswordEncoder getpasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
