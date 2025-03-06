package dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Student;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class StudentDAO extends GenericDAO<Student> {
    public StudentDAO(EntityManager entityManager) {
        super(Student.class, entityManager);
    }

    public List<Student> getStudentsWithSpecifcRank(String rank) {
        return entityManager.createQuery(
                        "SELECT s FROM Student s WHERE s.rank = :rank",
                        Student.class)
                .setParameter("rank", rank)
                .getResultList();
    }

    public List<Student> getStudentsWithProgressReportsInLastThreeMonths() {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        return entityManager.createQuery(
                        "SELECT s FROM Student s JOIN s.progressReports pr WHERE pr.reportDate >= :threeMonthsAgo",
                        Student.class)
                .setParameter("threeMonthsAgo", threeMonthsAgo)
                .getResultList();
    }

    public List<Student> getStudentsWhoJoinedWithinLast6Months() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);

        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);

        cq.select(student)
                .where(cb.greaterThanOrEqualTo(student.get("joinDate"), sixMonthsAgo));

        return entityManager.createQuery(cq).getResultList();
    }
}
