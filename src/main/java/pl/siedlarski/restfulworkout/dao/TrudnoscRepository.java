package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.Cwiczenie;
import pl.siedlarski.restfulworkout.entity.Trudnosc;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TrudnoscRepository  extends JpaRepository<Trudnosc, Long> {
}
