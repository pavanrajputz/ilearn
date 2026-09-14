package in.devigo.ilearn.trainee.services;

import in.devigo.ilearn.exception.ResourceNotFound;
import in.devigo.ilearn.security.SecurityUtils;
import in.devigo.ilearn.trainee.dtos.WorkExperienceRequest;
import in.devigo.ilearn.trainee.dtos.WorkExperienceResponse;
import in.devigo.ilearn.trainee.entities.TraineeProfile;
import in.devigo.ilearn.trainee.entities.WorkExperience;
import in.devigo.ilearn.trainee.repositories.TraineeRepository;
import in.devigo.ilearn.trainee.repositories.WorkExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkExperienceServiceImpl implements WorkExperienceService {


    private final WorkExperienceRepository workRepo;
    private final TraineeRepository traineeRepo;


    @Override
    public WorkExperienceResponse createWorkExperience(
            Long traineeId,
            WorkExperienceRequest request) {

        TraineeProfile profile = traineeRepo.findByUserId(traineeId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Trainee pofile not found"
                        )
                );

        WorkExperience workExperience = WorkExperience.builder()
                .trainee(profile)
                .companyName(request.getCompanyName())
                .jobTitle(request.getJobTitle())
                .employmentType(request.getEmploymentType())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .currentlyWorking(request.getCurrentlyWorking())
                .description(request.getDescription())
                .build();

        validateDates(workExperience);

        WorkExperience saved =  workRepo.save(workExperience);

        return mapToResponse(saved);
    }


    @Transactional(readOnly = true)
    @Override
    public List<WorkExperienceResponse> getMyWorkExperiences(
            Long traineeId
    ) {
        TraineeProfile profile = traineeRepo.findByUserId(traineeId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Trainee pofile not found"
                        )
                );

        return workRepo.
                findByTraineeProfileId(profile.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public WorkExperienceResponse getWorkExperienceById(
            Long traineeId,
            Long id) {
        TraineeProfile profile = traineeRepo.findByUserId(traineeId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Trainee pofile not found"
                        )
                );

        WorkExperience workExperience =
                workRepo.findByIdAndTraineeProfileId(
                        id,
                        profile.getId()
                )
                        .orElseThrow(() ->
                                new ResourceNotFound(
                                        "Work experience not found"
                                )
                        );

        return mapToResponse(workExperience);
    }

    @Override
    public WorkExperienceResponse updateWorkExperience(
            Long traineeId,
            Long id,
            WorkExperienceRequest request) {

        TraineeProfile profile = traineeRepo.findByUserId(traineeId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Trainee not found"
                        )
                );

        WorkExperience workExperience =
                workRepo.findByIdAndTraineeProfileId(
                                id,
                                profile.getId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFound(
                                        "Work experience not found"
                                )
                        );

        workExperience.setCompanyName(request.getCompanyName());
        workExperience.setJobTitle(request.getJobTitle());
        workExperience.setEmploymentType(request.getEmploymentType());
        workExperience.setStartDate(request.getStartDate());
        workExperience.setEndDate(request.getEndDate());
        workExperience.setCurrentlyWorking(request.getCurrentlyWorking());
        workExperience.setDescription(request.getDescription());

        validateDates(workExperience);

        return mapToResponse(workExperience);
    }

    @Override
    public void deleteWorkExperience(
            Long traineeId,
            Long id) {

        TraineeProfile profile = traineeRepo.findByUserId(traineeId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Trainee pofile not found"
                        )
                );

        WorkExperience workExperience =
                workRepo
                        .findByIdAndTraineeProfileId(
                                id,
                                profile.getId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Work experience not found"
                                )
                        );

        workRepo.delete(workExperience);
    }

    private void validateDates(WorkExperience workExperience) {

        if (workExperience.isCurrentlyWorking()
                && workExperience.getEndDate() != null) {

            throw new IllegalArgumentException(
                    "End date must be null when currently working"
            );
        }

        if (!workExperience.isCurrentlyWorking()
                && workExperience.getEndDate() != null
                && workExperience.getEndDate()
                .isBefore(workExperience.getStartDate())) {

            throw new IllegalArgumentException(
                    "End date cannot be before start date"
            );
        }
    }

    private WorkExperienceResponse mapToResponse(
            WorkExperience workExperience
    ) {

        return WorkExperienceResponse.builder()
                .id(workExperience.getId())
                .companyName(workExperience.getCompanyName())
                .jobTitle(workExperience.getJobTitle())
                .employmentType(workExperience.getEmploymentType())
                .startDate(workExperience.getStartDate())
                .endDate(workExperience.getEndDate())
                .currentlyWorking(workExperience.isCurrentlyWorking())
                .description(workExperience.getDescription())
                .build();
    }
}
