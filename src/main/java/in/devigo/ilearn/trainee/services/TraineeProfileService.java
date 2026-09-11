package in.devigo.ilearn.trainee.services;

import in.devigo.ilearn.trainee.dtos.TraineeProfileRequest;
import in.devigo.ilearn.trainee.dtos.TraineeProfileResponse;
import org.springframework.stereotype.Service;


public interface TraineeProfileService {

    TraineeProfileResponse createProfile(
            Long userId,
            TraineeProfileRequest request
    );

    TraineeProfileResponse getProfile(Long userId);

    TraineeProfileResponse updateProfile(
            Long userId,
            TraineeProfileRequest request
    );
}
