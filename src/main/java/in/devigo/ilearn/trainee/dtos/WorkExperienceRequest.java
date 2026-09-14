package in.devigo.ilearn.trainee.dtos;

import in.devigo.ilearn.trainee.entities.EmploymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Getter
public class WorkExperienceRequest {

    @NotBlank
    @Size(min = 5, max = 100)
    private String companyName;

    @NotBlank
    @Size(min = 5, max = 100)
    private String jobTitle;

    @NotNull(message = "Employment type is required")
    private EmploymentType employmentType;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull(message = "Currently working field is required")
    private Boolean currentlyWorking;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;
}
