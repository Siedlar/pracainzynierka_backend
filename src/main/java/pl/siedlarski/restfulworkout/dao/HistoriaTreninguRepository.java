package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.HistoriaTreningu;
import pl.siedlarski.restfulworkout.entity.User;
import pl.siedlarski.restfulworkout.entity.Wymiar;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HistoriaTreninguRepository extends JpaRepository<HistoriaTreningu, Long> {
    List<HistoriaTreningu> findByUser(User user);
}
