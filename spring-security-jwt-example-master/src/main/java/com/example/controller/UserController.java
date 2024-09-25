package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody User user){
         try {
                 userService.savedUser(user);
             return ResponseEntity.status(HttpStatus.OK).body("signup success");
         }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signup failed");
         }
    }
}
