package com.victordev.encuesta.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserLoginResquestModel {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8, max = 40)
    private String password;
}
