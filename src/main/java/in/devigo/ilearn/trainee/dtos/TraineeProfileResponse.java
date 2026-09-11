package in.devigo.ilearn.trainee.dtos;

import in.devigo.ilearn.trainee.entities.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TraineeProfileResponse {
    private Long id;

    private Long userId;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Gender gender;

    private String profilePhotoUrl;

    private String bio;

    private String designation;

    private String organization;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
