package com.example.controller;

import com.example.entity.AuthenticationRequest;
import com.example.service.UserDetailService;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;

    @PostMapping("/login")
    public String logIn(@RequestBody AuthenticationRequest request){
       try {
           authenticationManager
                   .authenticate(new UsernamePasswordAuthenticationToken
                           (request.getUsername(), request.getPassword()));
           UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
       }catch (Exception e){
           log.info("jdsfkjsdghfjksd");
       }
        return null;
    }
}
