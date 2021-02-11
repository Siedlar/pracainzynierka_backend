package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.Cwiczenie;
import pl.siedlarski.restfulworkout.entity.HistoriaCwiczen;
import pl.siedlarski.restfulworkout.entity.HistoriaTreningu;
import pl.siedlarski.restfulworkout.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HistoriaCwiczenRepository extends JpaRepository<HistoriaCwiczen, Long> {
}
