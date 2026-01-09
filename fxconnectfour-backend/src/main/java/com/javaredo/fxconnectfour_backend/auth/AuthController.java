package com.javaredo.fxconnectfour_backend.auth;

import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final AuthService authService;

    public AuthController(AuthenticationManager authManager,AuthService authService) {
        this.authManager = authManager;
        this.authService = authService;
    }

    public record LoginRequest(String username, String password) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @RequestBody LoginRequest req,
        HttpServletRequest httpRequest) {
            System.out.println(req.username);
            System.out.println(req.password);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(req.username, req.password);

        try {
            Authentication auth = authManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            httpRequest.getSession(true); // create session if not exists
            return ResponseEntity.ok("Login successful");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(
        @RequestBody LoginRequest req,
        HttpServletRequest httpRequest) {
            System.out.println(req.username);
            System.out.println(req.password);
        
        // add some username and password validation
        // username between 3 and 15 chars
        // - have at least one upper case letter
        // password must be
        // - have at least  one Lower case letter
        // - be at least 8 chars long
        // - have at least one special charachter 

        try{
            authService.register(req.username,req.password);
            return ResponseEntity.ok("user succesfully registered");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User alredy exists");
        }
    }


}
