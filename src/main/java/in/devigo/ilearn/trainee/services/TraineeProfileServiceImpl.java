package in.devigo.ilearn.trainee.services;

import in.devigo.ilearn.exception.DuplicateResourceException;
import in.devigo.ilearn.exception.ResourceNotFound;
import in.devigo.ilearn.trainee.dtos.TraineeProfileRequest;
import in.devigo.ilearn.trainee.dtos.TraineeProfileResponse;
import in.devigo.ilearn.trainee.entities.TraineeProfile;
import in.devigo.ilearn.trainee.repositories.TraineeRepository;
import in.devigo.ilearn.user.entities.User;
import in.devigo.ilearn.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TraineeProfileServiceImpl implements TraineeProfileService {


    //dependency injection for trainee and user's repositories
    private final TraineeRepository traineeRepo;
    private final UserRepository userRepo;


    //overrided method of the interface
    @Override
    public TraineeProfileResponse createProfile(Long userId, TraineeProfileRequest request) {

        //checking if the trainee exist or not
        if(traineeRepo.existsByUserId(userId)){
            throw new DuplicateResourceException("Trainee profile already exists");
        }

        //checking if the user exits or not
        User user = userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFound("User not found")
                );

        //creating the actual trainee object
        TraineeProfile traineeProfile = TraineeProfile.builder()
                .user(user)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .profilePhotoUrl(request.getProfilePhotoUrl())
                .bio(request.getBio())
                .designation(request.getDesignation())
                .organization(request.getOrganization())
                .build();

        //saving it in database
        TraineeProfile saved = traineeRepo.save(traineeProfile);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TraineeProfileResponse getProfile(Long userId) {
        TraineeProfile profile = traineeRepo.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Trainee profile not found"
                        )
                );

        return mapToResponse(profile);
    }

    @Override
    public TraineeProfileResponse updateProfile(Long userId, TraineeProfileRequest request) {
        TraineeProfile profile = traineeRepo.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFound(
                                "Trainee profile not found"
                        )
                );

        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setGender(request.getGender());
        profile.setProfilePhotoUrl(request.getProfilePhotoUrl());
        profile.setBio(request.getBio());
        profile.setDesignation(request.getDesignation());
        profile.setOrganization(request.getOrganization());

        return mapToResponse(profile);
    }

    private TraineeProfileResponse mapToResponse(TraineeProfile profile){
        return TraineeProfileResponse.builder()
                .id(profile.getId())
                .userId(profile.getUser().getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .dateOfBirth(profile.getDateOfBirth())
                .gender(profile.getGender())
                .profilePhotoUrl(profile.getProfilePhotoUrl())
                .bio(profile.getBio())
                .designation(profile.getDesignation())
                .organization(profile.getOrganization())
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .build();
    }
}
