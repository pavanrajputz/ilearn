package in.devigo.ilearn.trainee.contollers;


import in.devigo.ilearn.trainee.dtos.TraineeProfileRequest;
import in.devigo.ilearn.trainee.dtos.TraineeProfileResponse;
import in.devigo.ilearn.trainee.services.TraineeProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainees/profile")
@RequiredArgsConstructor
public class TraineeProfileController {

    private final TraineeProfileService service;

    @PostMapping
    public ResponseEntity<TraineeProfileResponse> createProfile(
            @Valid @RequestBody TraineeProfileRequest request,
            Authentication authentication
    ){
        Long userId = Long.valueOf(authentication.getName());

        TraineeProfileResponse response =
                service.createProfile(userId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<TraineeProfileResponse> getProfile(
            Authentication authentication
    ){
        Long userId = Long.valueOf(authentication.getName());

        TraineeProfileResponse response =
                service.getProfile(userId);

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<TraineeProfileResponse> updateProfile(
            @Valid @RequestBody TraineeProfileRequest request,
            Authentication authentication
    ){
        Long userId = Long.valueOf(authentication.getName());

        TraineeProfileResponse response =
                service.updateProfile(userId, request);

        return ResponseEntity.ok(response);
    }
}
