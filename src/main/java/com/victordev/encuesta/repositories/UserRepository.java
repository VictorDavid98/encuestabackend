package com.victordev.encuesta.repositories;

import org.springframework.data.repository.CrudRepository;

import com.victordev.encuesta.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
    
}
