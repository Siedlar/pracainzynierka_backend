package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.User;
import pl.siedlarski.restfulworkout.entity.UserInfo;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    Integer deleteByUsername(String username);
//    Optional<User> findByPasswordResetToken(String token);
@Transactional
    @Modifying
    @Query("update User u set u.password = :password where u.username = :username")
    int updateUserSetPasswordforUsername(@Param("username")String username, @Param("password")String password);
    @Transactional
    @Modifying
    @Query("update User u set u.email = :email where u.username = :username")
    int updateUserSetEmailforUsername(@Param("email")String email, @Param("username")String username);
    @Transactional
    @Modifying
    @Query("update User u set u.username = :username where u.username = :login")
    int updateUserSetLoginforUsername(@Param("login")String login, @Param("username")String username);

    UserInfo findUserInfoByUsername(String username);
}
