package in.devigo.ilearn.trainee.dtos;

import in.devigo.ilearn.trainee.entities.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TraineeProfileRequest {

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    private String lastName;

    private LocalDate dateOfBirth;

    private Gender gender;

    @Size(max = 500, message = "Profile photo URL cannot exceed 500 characters")
    private String profilePhotoUrl;

    private String bio;

    @Size(max = 150, message = "Designation cannot exceed 150 characters")
    private String designation;

    @Size(max = 200, message = "Organization cannot exceed 200 characters")
    private String organization;
}
