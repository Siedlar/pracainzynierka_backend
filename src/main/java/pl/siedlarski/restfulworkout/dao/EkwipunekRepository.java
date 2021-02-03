package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.Cwiczenie;
import pl.siedlarski.restfulworkout.entity.Ekwipunek;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EkwipunekRepository extends JpaRepository<Ekwipunek, Long> {
}
