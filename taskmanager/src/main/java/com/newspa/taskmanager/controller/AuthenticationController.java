package com.newspa.taskmanager.controller;

import com.newspa.taskmanager.dto.AuthLoginRequest;
import com.newspa.taskmanager.dto.AuthResponse;
import com.newspa.taskmanager.service.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name="Authenticacion JWT", description = "API for managing the authentication for crud operations in tasks")
public class AuthenticationController {

    private UserDetailsServiceImpl userDetailsService;

    public AuthenticationController (UserDetailsServiceImpl userDetailsService){
        this.userDetailsService =userDetailsService;
    }


    @PostMapping("/login")
    @Operation(summary = "authenticating users for admins tasks")
    @ApiResponse(responseCode = "200", description = "Success Authentication")
    @ApiResponse(responseCode = "403", description = "Forbidden Authentication")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailsService.login(userRequest), HttpStatus.OK);
    }
}
