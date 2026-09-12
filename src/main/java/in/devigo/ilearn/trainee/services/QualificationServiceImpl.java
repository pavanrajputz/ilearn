package in.devigo.ilearn.trainee.services;

import in.devigo.ilearn.exception.ResourceNotFound;
import in.devigo.ilearn.trainee.dtos.QualificationRequest;
import in.devigo.ilearn.trainee.dtos.QualificationResponse;
import in.devigo.ilearn.trainee.entities.Qualification;
import in.devigo.ilearn.trainee.entities.TraineeProfile;
import in.devigo.ilearn.trainee.repositories.QualificationRepository;
import in.devigo.ilearn.trainee.repositories.TraineeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepo;
    private final TraineeRepository traineeRepo;

    @Override
    public QualificationResponse createQualification(Long traineeId, QualificationRequest request) {
        TraineeProfile profile = traineeRepo.findByUserId(traineeId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Trainee pofile not found"
                        )
                );

        Qualification qualification = Qualification.builder()
                .trainee(profile)
                .degree(request.getDegree())
                .specialization(request.getSpecialization())
                .institution(request.getInstitute())
                .startYear(request.getStartYear())
                .endYear(request.getEndYear())
                .build();

        Qualification saved = qualificationRepo.save(qualification);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QualificationResponse> getQualifications(Long traineeId) {
        return qualificationRepo.findByTraineeId(traineeId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public QualificationResponse getQualification(Long traineeId, Long qualificationId) {
        Qualification qualification =
                qualificationRepo.findByIdAndTraineeId(
                        qualificationId,
                        traineeId
                ).orElseThrow(() ->
                        new ResourceNotFound(
                                "Qualification not found"
                        )
                );

        return mapToResponse(qualification);
    }

    @Override
    public QualificationResponse updateQualification(Long traineeId, Long qualificationId, QualificationRequest request) {
        Qualification qualification =
                qualificationRepo.findByIdAndTraineeId(
                        qualificationId,
                        traineeId
                ).orElseThrow(() ->
                        new ResourceNotFound(
                                "Qualification not found"
                        )
                );

        qualification.setDegree(request.getDegree());
        qualification.setSpecialization(request.getSpecialization());
        qualification.setInstitution(request.getInstitute());
        qualification.setStartYear(request.getStartYear());
        qualification.setEndYear(request.getEndYear());
        qualification.setGrade(request.getGrad());

        return mapToResponse(qualification);
    }


    @Override
    public void DeleteQualification(Long traineeId, Long qualificationId) {
        Qualification qualification =
                qualificationRepo.findByIdAndTraineeId(
                        qualificationId,
                        traineeId
                ).orElseThrow(() ->
                        new ResourceNotFound(
                                "Qualification not found"
                        )
                );

        qualificationRepo.delete(qualification);
    }


    private QualificationResponse mapToResponse(Qualification qualification) {
        return QualificationResponse.builder()
                .id(qualification.getId())
                .traineeId(qualification.getTrainee().getId())
                .degree(qualification.getDegree())
                .specialization(qualification.getSpecialization())
                .institute(qualification.getInstitution())
                .startYear(qualification.getStartYear())
                .endYear(qualification.getEndYear())
                .grade(qualification.getGrade())
                .build();
    }

}
