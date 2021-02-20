package pl.siedlarski.restfulworkout.security.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.siedlarski.restfulworkout.dao.PasswordResetTokenRepository;
import pl.siedlarski.restfulworkout.entity.PasswordResetToken;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceResetTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;
    @Test
    void validatePasswordResetTokenNoExisting() {
        String token="450b112e7af-851f-42cc-b0a5-49c62dcee977";
        PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        assertNull(passToken);
    }
}