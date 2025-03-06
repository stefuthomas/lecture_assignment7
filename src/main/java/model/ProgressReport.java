package model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "progress_report")
public class ProgressReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reportDate;

    private String achievement;

    private String areasForImprovement;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
