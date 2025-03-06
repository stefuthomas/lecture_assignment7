package dao;



import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.TrainingSession;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TrainingSessionDAO extends GenericDAO<TrainingSession> {
    public TrainingSessionDAO(EntityManager entityManager) {
        super(TrainingSession.class, entityManager);
    }

    public List<TrainingSession> getTrainingSessionsByStudentId(Long studentId) {
        return entityManager.createQuery(
                        "SELECT ts FROM TrainingSession ts JOIN ts.attendances a WHERE a.student.id = :studentId",
                        TrainingSession.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }

    public List<TrainingSession> getTrainingSessionsByLocation(String location) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TrainingSession> cq = cb.createQuery(TrainingSession.class);
        Root<TrainingSession> trainingSession = cq.from(TrainingSession.class);

        cq.select(trainingSession)
                .where(cb.equal(trainingSession.get("location"), location));

        return entityManager.createQuery(cq).getResultList();
    }
}
