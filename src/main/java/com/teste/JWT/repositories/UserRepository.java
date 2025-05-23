package com.teste.JWT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.teste.JWT.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    public UserDetails findByEmail(String email);
}
