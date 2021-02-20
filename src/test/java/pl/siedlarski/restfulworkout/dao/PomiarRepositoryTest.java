package pl.siedlarski.restfulworkout.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import pl.siedlarski.restfulworkout.RestfulWorkoutApplication;
import pl.siedlarski.restfulworkout.entity.User;
import pl.siedlarski.restfulworkout.entity.Wymiar;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PomiarRepositoryTest {
//    @Autowired
//    private PomiarRepository pomiarRepository;
//
//    @Test
//
//    void findByUser() {
//        String username="krysie";
//        User user = new User();
//                user.setUsername(username);
//        List<Wymiar> wymiarList=pomiarRepository.findByUser(user);
//        assertFalse(wymiarList.isEmpty());
//
//    }
}