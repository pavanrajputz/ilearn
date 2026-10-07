package in.devigo.ilearn.certificate.repository;

import in.devigo.ilearn.certificate.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByTraineeIdAndIsDeletedFalse(Long traineeId);

    Optional<Certificate> findByIdAndTraineeIdAndIsDeletedFalse(
            Long id,
            Long traineeId
    );
}
