package in.devigo.ilearn.trainee.repositories;

import in.devigo.ilearn.trainee.entities.TraineeProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<TraineeProfile, Long> {

    Optional<TraineeProfile> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
