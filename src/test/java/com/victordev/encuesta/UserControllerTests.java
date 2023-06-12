package com.victordev.encuesta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


import com.victordev.encuesta.models.requests.UserRegisterRequestModel;
import com.victordev.encuesta.models.responses.UserRest;
import com.victordev.encuesta.models.responses.ValidationErrors;
import com.victordev.encuesta.repositories.UserRepository;
import com.victordev.encuesta.services.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTests {
    private static final String API_URL = "/users";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void cleanup(){
        userRepository.deleteAll();
    }
    @Test
    public void createUser_sinNingunDato_retornaBadRequest(){
        ResponseEntity<Object> response = register(new UserRegisterRequestModel(), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
    @Test
    public void createUser_sinElCampoNombre_retornaBadRequest(){
        UserRegisterRequestModel user = TestUtil.createValidUser();
        user.setName(null);
        ResponseEntity<Object> response = register(user, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
    @Test
    public void createUser_sinElCampoPassword_retornaBadRequest(){
        UserRegisterRequestModel user = TestUtil.createValidUser();
        user.setPassword(null);
        ResponseEntity<Object> response = register(user, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
    @Test
    public void createUser_sinElCampoEmail_retornaBadRequest(){
        UserRegisterRequestModel user = TestUtil.createValidUser();
        user.setEmail(null);
        ResponseEntity<Object> response = register(user, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
    @Test
    public void createUser_sinNingunDato_retornaErroresDeValidacion(){
        ResponseEntity<ValidationErrors> response = register(new UserRegisterRequestModel(), ValidationErrors.class);
        Map<String , String> errors = response.getBody().getErrors();
        
        assertEquals(errors.size(), 3);
    }
    @Test
    public void createUser_sinNombre_retornaMensadeDeErrorDeValidacionParaElNombre(){
        UserRegisterRequestModel user = TestUtil.createValidUser();
        user.setName(null);
        ResponseEntity<ValidationErrors> response = register(user, ValidationErrors.class);
        Map<String , String> errors = response.getBody().getErrors();
        
        assertTrue(errors.containsKey("name"));
    }
    @Test
    public void createUser_sinEmail_retornaMensadeDeErrorDeValidacionParaElEmail(){
        UserRegisterRequestModel user = TestUtil.createValidUser();
        user.setEmail(null);
        ResponseEntity<ValidationErrors> response = register(user, ValidationErrors.class);
        Map<String , String> errors = response.getBody().getErrors();
        
        assertTrue(errors.containsKey("email"));
    }
    @Test
    public void createUser_sinPassword_retornaMensadeDeErrorDeValidacionParaElPassowrd(){
        UserRegisterRequestModel user = TestUtil.createValidUser();
        user.setPassword(null);
        ResponseEntity<ValidationErrors> response = register(user, ValidationErrors.class);
        Map<String , String> errors = response.getBody().getErrors();
        
        assertTrue(errors.containsKey("password"));
    }
    @Test
    public void createUser_conUsuarioValido_retornaOk(){
        UserRegisterRequestModel user = TestUtil.createValidUser();
        ResponseEntity<UserRest> response = register(user, UserRest.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void createUser_conUsuarioValido_retornaUserRest(){
        UserRegisterRequestModel user = TestUtil.createValidUser();
        ResponseEntity<UserRest> response = register(user, UserRest.class);
        assertEquals(response.getBody().getName(), user.getName());
    }

    public <T> ResponseEntity<T> register(UserRegisterRequestModel data, Class<T> responseType){
        return testRestTemplate.postForEntity(API_URL, data, responseType);
    }
}
