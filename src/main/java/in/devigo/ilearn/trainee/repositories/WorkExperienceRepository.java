package in.devigo.ilearn.trainee.repositories;

import in.devigo.ilearn.trainee.entities.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
    List<WorkExperience> findByTraineeProfileId(Long id);

    Optional<WorkExperience> findByIdAndTraineeProfileId(Long id, Long traineeProfileId);

}
