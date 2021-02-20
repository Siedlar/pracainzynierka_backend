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
class PomiarControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    void savePomiarBodyNull() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String content = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";
        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");
        System.out.println(token);
        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/pomiar/savePomiar")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest());
    }
    @Test
    void savePomiar() throws Exception {
        String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String content = "{\n" +
                "    \"talia\":100,\n" +
                "    \"data\":\"2023-10-10\"\n" +
                "}";
        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");
        System.out.println(token);
        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/pomiar/savePomiar").contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void getPomiary() throws Exception {

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
         mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/pomiar/getPomiary")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

    }

    @Test
    void deletePomiar() throws Exception {
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
        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/pomiar/savePomiar").contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}