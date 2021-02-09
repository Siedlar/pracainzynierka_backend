package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.JednoCwiczenie;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface JednoCwiczenieRepository extends JpaRepository<JednoCwiczenie, Long> {
}
