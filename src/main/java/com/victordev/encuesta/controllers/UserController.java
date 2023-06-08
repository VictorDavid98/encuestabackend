package com.victordev.encuesta.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victordev.encuesta.models.requests.UserRegisterRequestModel;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping()
    public String createUser(@RequestBody @Valid UserRegisterRequestModel userModel) {
        return "Create User 2";
    }
    
}
