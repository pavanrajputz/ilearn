package in.devigo.ilearn.trainee.dtos;

import in.devigo.ilearn.trainee.entities.EmploymentType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WorkExperienceResponse {

    private Long id;

    private String companyName;

    private String jobTitle;

    private EmploymentType employmentType;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean currentlyWorking;

    private String description;
}
