package com.victordev.encuesta.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.victordev.encuesta.entities.UserEntity;
import com.victordev.encuesta.models.requests.UserRegisterRequestModel;
import com.victordev.encuesta.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService {

    
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserRegisterRequestModel user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setEncryptedPassword(user.getPassword());
        return userRepository.save(userEntity);
    }
    
}
