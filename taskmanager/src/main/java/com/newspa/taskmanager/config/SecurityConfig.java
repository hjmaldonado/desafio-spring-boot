package com.newspa.taskmanager.config;


import com.newspa.taskmanager.util.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private JwtUtils jwtUtils;



    public SecurityConfig (JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http->{

                       http.requestMatchers("auth/**","/v3/api-docs/**","/swagger-ui/**","api-docs/","/api-docs/**","/api-docs").permitAll()
                           .requestMatchers("auth/**").permitAll()
                           .requestMatchers(HttpMethod.GET,"tasks/**").hasAnyAuthority("ROLE_ADMIN")
                           .requestMatchers(HttpMethod.POST,"tasks/**").hasAnyAuthority("ROLE_ADMIN")
                           .requestMatchers(HttpMethod.PUT,"tasks/**").hasAnyAuthority("ROLE_ADMIN")
                           .requestMatchers(HttpMethod.DELETE,"tasks/**").hasAnyAuthority("ROLE_ADMIN")
                           .anyRequest().denyAll();
                })

                .addFilterBefore(new JwtTokenValidator(jwtUtils), UsernamePasswordAuthenticationFilter.class).build();
    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
