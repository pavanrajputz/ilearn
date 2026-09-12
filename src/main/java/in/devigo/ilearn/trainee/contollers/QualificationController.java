package in.devigo.ilearn.trainee.contollers;

import in.devigo.ilearn.security.SecurityUtils;
import in.devigo.ilearn.trainee.dtos.QualificationRequest;
import in.devigo.ilearn.trainee.dtos.QualificationResponse;
import in.devigo.ilearn.trainee.services.QualificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainees/qualifications")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TRAINEE')")
public class QualificationController {

    private final QualificationService service;
    private final SecurityUtils securityUtils;

    @PostMapping
    public ResponseEntity<QualificationResponse> createQualification(
            @Valid @RequestBody QualificationRequest request
    ){
        Long traineeId = securityUtils.getCurrentUserId();

        QualificationResponse response =
                service.createQualification(traineeId, request);

        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<QualificationResponse>> getQualifications(){
        Long traineeId = securityUtils.getCurrentUserId();

        List<QualificationResponse> response =
                service.getQualifications(traineeId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{qualificationId}")
    public ResponseEntity<QualificationResponse> getQualification(
            @PathVariable Long qualificationId
    ){
        Long traineeId = securityUtils.getCurrentUserId();

        QualificationResponse response =
                service.getQualification(traineeId, qualificationId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{qualificationId}")
    public ResponseEntity<QualificationResponse> updateQualification(
            @PathVariable Long qualificationId,
            @Valid @RequestBody QualificationRequest request
    ){
        Long traineeId = securityUtils.getCurrentUserId();

        QualificationResponse response =
                service.updateQualification(traineeId,
                        qualificationId,
                        request);

        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{qualificationId}")
    public ResponseEntity<Void> deleteQualification(
            @PathVariable Long qualificationId
    ){
        Long traineeId = securityUtils.getCurrentUserId();

        service.deleteQualification(traineeId, qualificationId);

        return ResponseEntity.noContent().build();
    }
}
