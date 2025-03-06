package model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "training_sessions")
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long training_session_id;

    private LocalDate date;
    private String location;
    private int duration;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @OneToMany(mappedBy = "trainingSession", cascade = CascadeType.ALL)
    private List<Attendance> attendances;

    // Constructors, Getters & Setters
    public TrainingSession() {}

    public TrainingSession(LocalDate date, String location, int duration, Instructor instructor) {
        this.date = date;
        this.location = location;
        this.duration = duration;
        this.instructor = instructor;
    }

    public Long getTrainingSessionId() { return training_session_id; }
    public LocalDate getDate() { return date; }
    public String getLocation() { return location; }
    public int getDuration() { return duration; }
    public Instructor getInstructor() { return instructor; }
    public List<Attendance> getAttendances() { return attendances; }

    public void setDate(LocalDate date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
    public void setAttendances(List<Attendance> attendances) { this.attendances = attendances; }
}