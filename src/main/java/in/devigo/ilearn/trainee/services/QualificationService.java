package in.devigo.ilearn.trainee.services;

import in.devigo.ilearn.trainee.dtos.QualificationRequest;
import in.devigo.ilearn.trainee.dtos.QualificationResponse;

import java.util.List;

public interface QualificationService {

    QualificationResponse createQualification(
            Long traineeId,
            QualificationRequest request
    );

    List<QualificationResponse> getQualifications(Long traineeId);

    QualificationResponse getQualification(Long traineeId,
                                           Long qualificationId);

    QualificationResponse updateQualification(
            Long traineeId,
            Long qualificationId,
            QualificationRequest request
    );

    void DeleteQualification(
            Long traineeId,
            Long qualificationId
    );
}
