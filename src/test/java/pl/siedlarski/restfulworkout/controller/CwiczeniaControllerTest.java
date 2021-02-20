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

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CwiczeniaControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getCwiczenia() throws Exception {
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


        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/cwiczenia/getCwiczenia")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void getPartie() throws Exception {
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


        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/cwiczenia/getPartie")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void getTrudnosc() throws Exception {
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


        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/cwiczenia/getTrudnosc")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void getEkwipunek() throws Exception {
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


        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/cwiczenia/getEkwipunek")

                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void addCwiczenia() throws Exception {  String username = "krysie";
        String password = "qwerty";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.replace("{\"token\":\"", "");
        String token = response.replace("\",\"type\":\"Bearer\",\"id\":1,\"username\":\"krysie\",\"email\":\"ksiedlarski@gmail.com\",\"roles\":[\"ROLE_USER\"]}", "");

   body = "{\"nazwa_cwiczenia\":\"Squash1\",\"url_film\":\"https://www.youtube.com/embed/pHkSz77CUp4\",\"url_zdjecia\":\"https://s-trojmiasto.pl/zdj/c/n/9/2381/3000x0/2381014.jpg\",\"wskazowki\":\"asf\",\"trudnosc\":{\"trudnosc\":\"średniozaawansowany\",\"trudnosc_id\":2},\"partia\":{\"partia\":\"Kardio\",\"partia_id\":11},\"ekwipunek\":[{\"ekwipunek\":\"inne\",\"ekwipunek_id\":4}]}\n";

        mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/cwiczenia/addCwiczenia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}