package pl.siedlarski.restfulworkout.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    void resetPasswordNoUserWithEmail() throws Exception {
        String email = "test@test.pl";


        String body = "{\"email\":\"" + email+ "\"}" ;

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/resetPassword").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest()).andReturn();
    }
    @Test

    void resetPasswordUser() throws Exception {
        String email = "ksiedlarski@gmail.com";


        String body = "{\"email\":\"" + email+ "\"}" ;

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/resetPassword").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void registerUserSameEmail() throws Exception {
       String body="{\n" +
               "    \"username\":\"krysie\",\n" +
               "    \"email\":\"ksiedjlarski@gmail.com\",\n" +
               "    \"password\":\"qwerty\"\n" +
               "}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signup").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest()).andReturn();
    }
    @Test
    void registerUserSameLogin() throws Exception {
        String body="{\n" +
                "    \"username\":\"krysiek\",\n" +
                "    \"email\":\"ksiedlarski@gmail.com\",\n" +
                "    \"password\":\"qwerty\"\n" +
                "}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signup").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest()).andReturn();
    }
    @Test
    void registerUser() throws Exception {
        String body="{\n" +
                "    \"username\":\"krysiek\",\n" +
                "    \"email\":\"ksiedlaarski@gmail.com\",\n" +
                "    \"password\":\"qwerty\"\n" +
                "}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signup").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();
    }
    @Test
    void signIn() throws Exception {
        String body="{\n" +
                "    \"username\":\"krysie\",\n" +
                "    \"password\":\"qwerty\"\n" +
                "}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();
    }
    @Test
    void signInBadLogin() throws Exception {
        String body="{\n" +
                "    \"username\":\"krysieasffsa\",\n" +
                "    \"password\":\"qwerty\"\n" +
                "}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isUnauthorized()).andReturn();
    }

}