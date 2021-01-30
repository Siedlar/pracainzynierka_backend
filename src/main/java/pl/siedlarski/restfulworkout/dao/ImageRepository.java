package pl.siedlarski.restfulworkout.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.siedlarski.restfulworkout.entity.ImageModel;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
}
