package in.devigo.ilearn.trainee.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class QualificationRequest {

    @NotBlank(message = "Degree is required")
    @Size(max = 500, message = "Degree cannot exceeded the length.")
    private String degree;

    @Size(max = 500, message = "Specialization cannot exceeded the length.")
    private String specialization;

    @Size(max = 1000, message = "Institute exceeded the length.")
    private String institute;

    @Min(value = 1900, message = "Invalid start year.")
    @Max(value = 2100, message = "Invalid start year.")
    private Integer startYear;

    @Min(value = 1900, message = "Invalid start year.")
    @Max(value = 2100, message = "Invalid start year.")
    private Integer endYear;

    private BigDecimal grade;
}
