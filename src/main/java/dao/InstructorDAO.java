package dao;

import jakarta.persistence.criteria.CriteriaQuery;
import model.Instructor;
import jakarta.persistence.EntityManager;

import java.util.List;

public class InstructorDAO extends GenericDAO<Instructor> {
    public InstructorDAO(EntityManager entityManager) {
        super(Instructor.class, entityManager);
    }

    public List<Instructor> getInstructorsBySpecialization(String specialization) {
        return entityManager.createQuery(
                        "SELECT i FROM Instructor i WHERE i.specialization = :specialization",
                        Instructor.class)
                .setParameter("specialization", specialization)
                .getResultList();
    }

    public List<Instructor> getInstructorsWithMoreThanFiveYearsOfExperience() {
        CriteriaQuery<Instructor> cq = entityManager.getCriteriaBuilder().createQuery(Instructor.class);
        cq.select(cq.from(Instructor.class))
                .where(entityManager.getCriteriaBuilder().greaterThanOrEqualTo(cq.from(Instructor.class).get("experienceYears"), 5));

        return entityManager.createQuery(cq).getResultList();
    }
}
