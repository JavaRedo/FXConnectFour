package com.javaredo.fxconnectfour_backend.user;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/user")
public class AppUserController {
    
    public record UserResponse(
    Long id,
    String username,
    Set<String> roles
    ){}

    private final AppUserRepository repo;

    AppUserController(AppUserRepository repo){
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long id){
        AppUser user = repo.findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserResponse response = new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getRoles());

        ResponseEntity<UserResponse> resEnt = 
            new ResponseEntity<UserResponse>(response, HttpStatus.OK);
        
            return resEnt ;
    }  

}
