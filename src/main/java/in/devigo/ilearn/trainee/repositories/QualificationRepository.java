package in.devigo.ilearn.trainee.repositories;

import in.devigo.ilearn.trainee.entities.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    List<Qualification> findByTraineeId(Long traineeId);

    Optional<Qualification> findByIdAndTraineeId(
            Long qualificationId,
            Long traineeId
    );
}
