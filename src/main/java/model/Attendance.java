package model;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "training_session_id", nullable = false)
    private TrainingSession trainingSession;

    // Constructors, Getters & Setters
    public Attendance() {}

    public Attendance(boolean status, String notes, Student student, TrainingSession trainingSession) {
        this.status = status;
        this.notes = notes;
        this.student = student;
        this.trainingSession = trainingSession;
    }

    public Long getId() { return id; }
    public boolean isStatus() { return status; }
    public String getNotes() { return notes; }
    public Student getStudent() { return student; }
    public TrainingSession getTrainingSession() { return trainingSession; }

    public void setStatus(boolean status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setStudent(Student student) { this.student = student; }
    public void setTrainingSession(TrainingSession trainingSession) { this.trainingSession = trainingSession; }
}