package pl.siedlarski.restfulworkout.security.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import pl.siedlarski.restfulworkout.dao.UserRepository;
import pl.siedlarski.restfulworkout.entity.User;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class UserDetailsServiceImplTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MockMvc mvc;
    @Test
    void loadUserByNotExistingUserbyUsername() {
        //given
        String username="test1";


        //when

        Exception exception=assertThrows(UsernameNotFoundException.class,()->{userRepository.findByUsername(username) .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username"));;});
        String expectedMessage = "User Not Found with username";
        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void loadUserbyUsername() {
        //given
        String username="krysie";


        //when

       User user= userRepository.findByUsername(username).get();

        //then
       assertNotNull(user);
    }
}