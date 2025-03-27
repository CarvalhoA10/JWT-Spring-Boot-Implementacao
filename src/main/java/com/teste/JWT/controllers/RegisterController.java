package com.teste.JWT.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.JWT.models.UserModel;
import com.teste.JWT.others.NewUserDto;
import com.teste.JWT.others.UserRole;
import com.teste.JWT.repositories.UserRepository;

@RestController
@RequestMapping("register")
public class RegisterController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<UserModel> register(@RequestBody NewUserDto newUserDto){

        System.out.println(newUserDto.getEmail());

        UserModel user = new UserModel();
        BeanUtils.copyProperties(newUserDto, user);
        user.setUserRole(UserRole.basic);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user = this.userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }

}
