package in.devigo.ilearn.trainee.services;

import in.devigo.ilearn.trainee.dtos.WorkExperienceRequest;
import in.devigo.ilearn.trainee.dtos.WorkExperienceResponse;

import java.util.List;

public interface WorkExperienceService {

    WorkExperienceResponse createWorkExperience(
            Long userId,
            WorkExperienceRequest workExperienceRequest);

    List<WorkExperienceResponse> getMyWorkExperiences(
            Long userId
    );

    WorkExperienceResponse getWorkExperienceById(
            Long userId,
            Long id);

    WorkExperienceResponse updateWorkExperience(
            Long userId,
            Long id,
            WorkExperienceRequest workExperienceRequest);

    void deleteWorkExperience(
            Long userId,
            Long id);
}
