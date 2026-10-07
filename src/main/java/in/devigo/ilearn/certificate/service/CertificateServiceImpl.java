package in.devigo.ilearn.certificate.service;

import in.devigo.ilearn.certificate.dto.CertificateRequest;
import in.devigo.ilearn.certificate.dto.CertificateResponse;
import in.devigo.ilearn.certificate.entity.Certificate;
import in.devigo.ilearn.certificate.repository.CertificateRepository;
import in.devigo.ilearn.exception.DuplicateResourceException;
import in.devigo.ilearn.exception.ResourceNotFound;
import in.devigo.ilearn.trainee.entities.TraineeProfile;
import in.devigo.ilearn.trainee.repositories.TraineeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository repo;
    private final TraineeRepository traineeRepo;

    @Override
    public CertificateResponse createCertificate(Long userId, CertificateRequest request) {
        TraineeProfile profile = traineeRepo.findByUserIdAndIsDeletedFalse(
                userId
        ).orElseThrow(() ->
                new ResourceNotFound("Trainee profile not found"));

        Certificate crt = Certificate
                .builder()
                .trainee(profile)
                .title(request.getTitle())
                .issuer(request.getIssuer())
                .issueDate(request.getIssueDate())
                .expiryDate(request.getExpiryDate())
                .credentialId(request.getCredentialId())
                .credentialUrl(request.getCredentialUrl())
                .certificateImageUrl(request.getCertificateImageUrl())
                .description(request.getDescription())
                .build();

        validateDates(crt);
        Certificate saved =  repo.save(crt);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CertificateResponse> getMyCertificates(Long userId) {

        TraineeProfile profile = traineeRepo.findByUserIdAndIsDeletedFalse(
                userId
        ).orElseThrow(() ->
                new ResourceNotFound("Trainee profile not found"));

        return repo.findByTraineeIdAndIsDeletedFalse(profile.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CertificateResponse getCertificateById(Long userId, Long certificateId) {
        TraineeProfile profile = traineeRepo.findByUserIdAndIsDeletedFalse(
                userId
        ).orElseThrow(() ->
                new ResourceNotFound("Trainee profile not found"));

        Certificate crt = repo.findByIdAndTraineeIdAndIsDeletedFalse(certificateId, profile.getId())
                .orElseThrow(() ->
                    new ResourceNotFound("Certificate not found"));

        return mapToResponse(crt);
    }

    @Override
    public CertificateResponse updateCertificate(Long userId, Long certificateId, CertificateRequest request) {
        TraineeProfile profile = traineeRepo.findByUserIdAndIsDeletedFalse(
                userId
        ).orElseThrow(() ->
                new ResourceNotFound("Trainee profile not found"));

        Certificate crt = repo.findByIdAndTraineeIdAndIsDeletedFalse(certificateId, profile.getId())
                .orElseThrow(() ->
                        new ResourceNotFound("Certificate not found"));

        crt.setTitle(request.getTitle());
        crt.setIssuer(request.getIssuer());
        crt.setIssueDate(request.getIssueDate());
        crt.setExpiryDate(request.getExpiryDate());
        crt.setCredentialId(request.getCredentialId());
        crt.setCredentialUrl(request.getCredentialUrl());
        crt.setCertificateImageUrl(request.getCertificateImageUrl());
        crt.setDescription(request.getDescription());

        validateDates(crt);

        Certificate saved = repo.save(crt);

        return mapToResponse(saved);
    }

    @Override
    public void deleteCertificate(Long userId, Long certificateId) {

        TraineeProfile profile = traineeRepo
                .findByUserIdAndIsDeletedFalse(userId)
                .orElseThrow(() ->
                        new ResourceNotFound("Trainee profile not found"));

        Certificate certificate = repo
                .findByIdAndTraineeIdAndIsDeletedFalse(
                        certificateId,
                        profile.getId()
                )
                .orElseThrow(() ->
                        new ResourceNotFound("Certificate not found"));

        certificate.setIsDeleted(true);

        repo.save(certificate);
    }

    private void validateDates(Certificate crt) {
        if(crt.getExpiryDate() != null &&
                crt.getIssueDate() != null &&
                crt.getExpiryDate().isBefore(crt.getIssueDate())){
            throw new IllegalArgumentException(
                    "Expiry date must be after issue date"
            );
        }
    }

    private CertificateResponse mapToResponse(Certificate certificate) {
        return CertificateResponse.builder()
                .id(certificate.getId())
                .title(certificate.getTitle())
                .issuer(certificate.getIssuer())
                .issueDate(certificate.getIssueDate())
                .expiryDate(certificate.getExpiryDate())
                .credentialId(certificate.getCredentialId())
                .credentialUrl(certificate.getCredentialUrl())
                .description(certificate.getDescription())
                .certificateImageUrl(
                        certificate.getCertificateImageUrl()
                )
                .createdAt(certificate.getCreatedAt())
                .updatedAt(certificate.getUpdatedAt())
                .build();
    }
}
