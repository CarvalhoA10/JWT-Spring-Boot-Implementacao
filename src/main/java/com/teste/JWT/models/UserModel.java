package com.teste.JWT.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.teste.JWT.others.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true, nullable = false)
    private String username;

    @Column(length = 150, unique = true, nullable = false)
    private String email;

    @Column(length = 150, nullable = false)
    private String password;

    @Column
    private UserRole userRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }


    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       if(this.getUserRole() == UserRole.basic){
            return List.of(new SimpleGrantedAuthority("USER_BASIC"));
       }else if(this.getUserRole() == UserRole.staff){
            return List.of(new SimpleGrantedAuthority("USER_STAFF"));
       }else if(this.getUserRole() == UserRole.admin){
            return List.of(new SimpleGrantedAuthority("USER_ADMIN"));
       }else{
        return null;
       }
    }

    

}
