package com.victordev.encuesta.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.victordev.encuesta.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
    public UserEntity findByEmail(String email);

    public UserEntity findById(long id);
}
