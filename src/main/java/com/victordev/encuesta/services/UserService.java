package com.victordev.encuesta.services;


import com.victordev.encuesta.entities.UserEntity;
import com.victordev.encuesta.models.requests.UserRegisterRequestModel;

public interface UserService {
    public UserEntity createUser(UserRegisterRequestModel user);
    
}
