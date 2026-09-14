package in.devigo.ilearn.trainee.contollers;

import in.devigo.ilearn.security.SecurityUtils;
import in.devigo.ilearn.trainee.dtos.WorkExperienceRequest;
import in.devigo.ilearn.trainee.dtos.WorkExperienceResponse;
import in.devigo.ilearn.trainee.entities.WorkExperience;
import in.devigo.ilearn.trainee.services.WorkExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainee/work-experiences")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final SecurityUtils securityUtils;
    private final WorkExperienceService service;

    @PostMapping
    public ResponseEntity<WorkExperienceResponse> createWorkExperience(
            @Valid @RequestBody WorkExperienceRequest request
    ){
        Long traineeId = securityUtils.getCurrentUserId();

        WorkExperienceResponse response =
                service.createWorkExperience(traineeId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<WorkExperienceResponse>> getMyWorkExperiences(){
        Long traineeId = securityUtils.getCurrentUserId();

        return ResponseEntity.ok(
                service
                        .getMyWorkExperiences(traineeId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkExperienceResponse> getWorkExperienceById(
            @PathVariable Long id){
        Long traineeId = securityUtils.getCurrentUserId();

        return ResponseEntity.ok(
                service.
                        getWorkExperienceById(traineeId, id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkExperienceResponse> updateWorkExperienceById(
            @PathVariable Long id,
            @Valid @RequestBody WorkExperienceRequest request
    ){
        Long traineeId = securityUtils.getCurrentUserId();

        return ResponseEntity.ok(
                service
                        .updateWorkExperience(
                                traineeId,
                                id,
                                request
                        )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkExperience(
            @PathVariable Long id
    ){
        Long userId = securityUtils.getCurrentUserId();

        service.deleteWorkExperience(userId, id);

        return ResponseEntity.noContent().build();
    }
}
