package com.teste.JWT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.JWT.models.UserModel;
import com.teste.JWT.others.LoginDto;
import com.teste.JWT.services.TokenService;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        var authenticate = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((UserModel) authenticate.getPrincipal());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
    }



}
