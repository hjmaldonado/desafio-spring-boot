package com.newspa.taskmanager.service;

import com.newspa.taskmanager.Exception.ApiException;
import com.newspa.taskmanager.dto.AuthLoginRequest;
import com.newspa.taskmanager.dto.AuthResponse;
import com.newspa.taskmanager.entity.UserEntity;
import com.newspa.taskmanager.repository.UserRepository;
import com.newspa.taskmanager.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(JwtUtils jwtUtils, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository= userRepository;
        this.jwtUtils=jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity userEntity = userRepository.findUserEntitiesByName(username)
               .orElseThrow(() ->  new ApiException("user " +
                       username + "doesn't exist", HttpStatus.NOT_FOUND));
       List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(userEntity.getRole().name())));
        return new User(userEntity.getName(),
                        userEntity.getPassword(),
                        userEntity.isEnabled(),
                        userEntity.isAccountNonExpired(),
                        userEntity.isCredentialsNonExpired(),
                        userEntity.isAccountNonLocked(),
                         authorityList);
    }

    public AuthResponse login(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();
        Authentication authentication = this.authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(username, "user logged success", accessToken, true);
        return  authResponse;
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails =  this.loadUserByUsername(username);
        if(userDetails == null){
           throw new BadCredentialsException("Invalid username or password");
        }
        if(passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
}
