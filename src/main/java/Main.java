
import dao.StudentDAO;
import dao.InstructorDAO;
import dao.TrainingSessionDAO;
import model.Student;
import model.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.TrainingSession;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create the EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aikidoPU");
        EntityManager em = emf.createEntityManager();

        // Initialize DAO classes
        StudentDAO studentDAO = new StudentDAO(em);
        InstructorDAO instructorDAO = new InstructorDAO(em);
        TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO(em);

        // Add sample students
        Student student1 = new Student("John Doe", "john@example.com", "White Belt", LocalDate.now());
        Student student2 = new Student("Jane Smith", "jane@example.com", "Yellow Belt", LocalDate.now());

        // Save students using the DAO (no need to call em.persist directly)
        //studentDAO.save(student1);
        //studentDAO.save(student2);

        // Add an instructor
        Instructor instructor = new Instructor("Sensei Aki", "Aikido Throws", 10);
        //instructorDAO.save(instructor);

        // Fetch and print students
        List<Student> students = studentDAO.findAll();
        System.out.println("Students:");
        students.forEach(s -> {
            System.out.println(s.getName() + " - " + s.getRank());
            System.out.println("Created At: " + s.getCreatedAt());
            System.out.println("Membership Duration: " + s.getMembershipDuration() + " years");
        });

        // Fetch and print instructors
        List<Instructor> instructors = instructorDAO.findAll();
        System.out.println("Instructors:");
        instructors.forEach(i -> System.out.println(i.getName() + " - " + i.getSpecialization()));

        // Retrieve all training sessions attended by a specific student.
        List<TrainingSession> trainingSessions = trainingSessionDAO.getTrainingSessionsByStudentId(1L);
        System.out.println("Training Sessions attended by student with ID 1:");
        trainingSessions.forEach(ts -> System.out.println(ts.getDate() + " - " + ts.getLocation()));

        // List all students who have a specific rank.
        List<Student> studentsWithSpecificRank = studentDAO.getStudentsWithSpecifcRank("Yellow Belt");
        System.out.println("Students with Yellow Belt rank:");
        studentsWithSpecificRank.forEach(s -> System.out.println(s.getName()));

        // Get all instructors specialized in a particular Aikido technique.
        List<Instructor> instructorsWithSpecialization = instructorDAO.getInstructorsBySpecialization("Aikido Throws");
        System.out.println("Instructors specialized in Aikido Throws:");
        instructorsWithSpecialization.forEach(i -> System.out.println(i.getName()));

        // Fetch students with progress reports in the last three months.
        List<Student> studentsWithProgressReports = studentDAO.getStudentsWithProgressReportsInLastThreeMonths();
        System.out.println("Students with progress reports in the last three months:");
        studentsWithProgressReports.forEach(s -> System.out.println(s.getName()));

        // Find all students who joined within the last six months.
        List<Student> studentsWhoJoinedWithinLast6Months = studentDAO.getStudentsWhoJoinedWithinLast6Months();
        System.out.println("Students who joined within the last six months:");
        studentsWhoJoinedWithinLast6Months.forEach(s -> System.out.println(s.getName()));

        // Search for training sessions held in a specific location.
        List<TrainingSession> trainingSessionsInLocation = trainingSessionDAO.getTrainingSessionsByLocation("Gym");
        System.out.println("Training Sessions held in 'Gym':");
        trainingSessionsInLocation.forEach(ts -> System.out.println(ts.getDate()));

        // Retrieve all instructors with more than five years of experience.
        List<Instructor> experiencedInstructors = instructorDAO.getInstructorsWithMoreThanFiveYearsOfExperience();
        System.out.println("Instructors with more than five years of experience:");
        experiencedInstructors.forEach(i -> System.out.println(i.getName()));
    }
}
