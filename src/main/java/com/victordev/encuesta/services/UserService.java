package com.victordev.encuesta.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.victordev.encuesta.entities.UserEntity;
import com.victordev.encuesta.models.requests.UserRegisterRequestModel;

public interface UserService extends UserDetailsService{

    public UserDetails loadUserByUsername(String email);

    public UserEntity getUser(String email);

    public UserEntity createUser(UserRegisterRequestModel user);
    
}
