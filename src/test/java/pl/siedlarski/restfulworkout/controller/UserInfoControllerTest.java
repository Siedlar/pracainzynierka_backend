package pl.siedlarski.restfulworkout.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
class UserInfoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void deleteUser() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String content = "{\n" +
                "    \"id\":1\n" +
                "}";
        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");
        System.out.println(token);
        mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/api/data/deleteUser")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void changeEmail() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String content = "{\n" +
                "    \"email\":\"ksiedlarski@gmail.pl\"\n" +
                "  \n" +
                "}";
        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");
        System.out.println(token);
        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/data/changeEmail").contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void changeLogin() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String content = "{\n" +
                "    \"username\":\"ksiedlarski\"\n" +
                "  \n" +
                "}";
        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");
        System.out.println(token);
        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/data/changeLogin").contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void getUserInfo() throws Exception {
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
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/data/getUserInfo")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
    @Test
    void getUserInfoWhereUserDontHaveUserInfo() throws Exception {
        String username = "test";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":2,\"username\":\"test\",\"email\":\"test@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");
        System.out.println(token);
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/data/getUserInfo")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest());
    }
    @Test
    void saveUserInfo() throws Exception {
        String username = "test";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":2,\"username\":\"test\",\"email\":\"test@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");

        String content = "{\n" +
                "   \"wzrost\":180\n" +
                "}";

        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/data/saveUserInfo").contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }


    @Test
    void getImageUserNoImagePic() throws Exception {
        String username = "test";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":2,\"username\":\"test\",\"email\":\"test@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");


        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/data/getImage")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest());
    }
    @Test
    void getImage() throws Exception {
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


        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/data/getImage")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}