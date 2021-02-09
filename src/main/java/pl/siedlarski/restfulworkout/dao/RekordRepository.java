package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.Cwiczenie;
import pl.siedlarski.restfulworkout.entity.HistoriaTreningu;
import pl.siedlarski.restfulworkout.entity.Rekord;
import pl.siedlarski.restfulworkout.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RekordRepository  extends JpaRepository<Rekord, Long> {
    Rekord findByUserAndCwiczenie(User user, Cwiczenie Cwiczenie);
    List<Rekord> findByUser(User user);
    Integer deleteByHistoriaTreningu(HistoriaTreningu historiaTreningu);
}
