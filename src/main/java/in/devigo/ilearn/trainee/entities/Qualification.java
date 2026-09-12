package in.devigo.ilearn.trainee.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "qualifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "trainee_id",
            nullable = false
    )
    private TraineeProfile traineeProfile;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String degree;

    @Column(columnDefinition = "LONGTEXT")
    private String specialization;

    @Column(columnDefinition = "LONGTEXT")
    private String institution;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column(precision = 5, scale = 2)
    private BigDecimal grade;
}
