package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.PasswordResetToken;
import pl.siedlarski.restfulworkout.entity.User;
import pl.siedlarski.restfulworkout.entity.Wymiar;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PomiarRepository extends JpaRepository<Wymiar, Long> {
    List<Wymiar> findByUser(User user);

}
