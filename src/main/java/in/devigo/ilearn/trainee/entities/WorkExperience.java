package in.devigo.ilearn.trainee.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "work_experience")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainee_id")
    private TraineeProfile traineeId;

    @Column(name = "company_name",  nullable = false)
    private String companyName;

    @Column(name = "job_title",  nullable = false)
    private String jobTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "employement_type",  nullable = false)
    private EmployementType employementType;

    @Column(name = "start_date",  nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "currently_working",  nullable = false)
    private boolean currentlyWorking;

    @Column(columnDefinition = "TEXT")
    private String description;
}
