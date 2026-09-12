package in.devigo.ilearn.trainee.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class QualificationResponse {

    private Long id;

    private Long traineeId;

    private String degree;

    private String specialization;

    private String institute;

    private Integer startYear;

    private Integer endYear;

    private BigDecimal grade;
}
