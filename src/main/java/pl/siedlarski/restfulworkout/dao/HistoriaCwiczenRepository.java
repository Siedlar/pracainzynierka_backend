package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.siedlarski.restfulworkout.entity.HistoriaCwiczen;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface HistoriaCwiczenRepository extends JpaRepository<HistoriaCwiczen, Long> {
}
