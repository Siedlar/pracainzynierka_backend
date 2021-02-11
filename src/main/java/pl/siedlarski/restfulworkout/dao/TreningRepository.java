package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.JednoCwiczenie;
import pl.siedlarski.restfulworkout.entity.Trening;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TreningRepository extends JpaRepository<Trening, Long> {
}
