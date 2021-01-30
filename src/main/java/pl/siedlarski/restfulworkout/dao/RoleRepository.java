package pl.siedlarski.restfulworkout.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.ERole;
import pl.siedlarski.restfulworkout.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}