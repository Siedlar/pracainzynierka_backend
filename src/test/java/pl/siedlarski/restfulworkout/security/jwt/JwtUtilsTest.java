package pl.siedlarski.restfulworkout.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JwtUtilsTest {
    JwtUtils jwtUtils =new JwtUtils();

    @Autowired
    private MockMvc mvc;
    @Test
    void getUserNameFromJwtToken() throws Exception {
        //given
        jwtUtils.setJwtSecret("siedlarskiSecretKey");

        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");
        System.out.println(token);

        //when
       String shouldReturnUsername=jwtUtils.getUserNameFromJwtToken(token);

        //then
        assertEquals(username,shouldReturnUsername);
    }

    @Test
    void validateJwtTokenFailJwtClaimIsEmpty() {
        //given
        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrcnlzaWUiLCJpYXQiOjE2MTMxNTY1MDMsImV4cCI6MTYxMzI0MjkwM30.7yx7Kh7Qiba4Is4gwFSPJTEWJERpD0cBqzlup1jdaiZYrD2Fek0WBbxfZX5BOVN8mZy3ylQXLNPqRsLw3WYfuw";

        //when
        Exception exception=assertThrows(IllegalArgumentException.class,()->{jwtUtils.validateJwtToken(token);});
        String expectedMessage = "JWT claims string is empty: {}";
        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedMessage));

    }
    @Test
    void validateJwtTokenIsCorrect(){

        //given
        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrcnlzaWUiLCJpYXQiOjE2MTMxNTY1MDMsImV4cCI6MTYxMzI0MjkwM30.7yx7Kh7Qiba4Is4gwFSPJTEWJERpD0cBqzlup1jdaiZYrD2Fek0WBbxfZX5BOVN8mZy3ylQXLNPqRsLw3WYfuw";
        jwtUtils.setJwtSecret("siedlarskiSecretKey");

        //when
        Exception exception=assertThrows(Exception.class,()->{jwtUtils.validateJwtToken(token);});
        String expectedMessage = "JWT token is expired: {}";
        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void existentUserCanGetToken() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();
//
//        String response = result.getResponse().getContentAsString();
//        response = response.replace("{\"access_token\": \"", "");
//        String token = response.replace("\"}", "");
//
//        mvc.perform(MockMvcRequestBuilders.get("/test")
//                .header("Authorization", "Bearer " + token))
//                .andExpect(status().isOk());
    }
    @Test
    public void existentUserCanGetTokenAndAuthenticate() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");
        System.out.println(token);
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/trening/getTreningi")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
    @Test
    public void noneUserCanGetToken() throws Exception {
        String username = "test";
        String password = "tescior";
        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isUnauthorized()).andReturn();
    }
    @Test
    void unathorizedAccess() throws Exception {
        String username = "test";
        String password = "test1234";
        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/treningi/getTreningi").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isUnauthorized()).andReturn();
    }
}