package re.edu.session3.repositories;

import org.springframework.stereotype.Repository;
import re.edu.session3.exception.ResourceNotFoundException;
import re.edu.session3.model.Enrollment;

import java.util.*;

@Repository
public class EnrollmentRepository implements IBaseRepository<Enrollment> {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        enrollments.add(new Enrollment(1, "Cuong", 1));
        enrollments.add(new Enrollment(2, "An", 2));
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    @Override
    public Optional<Enrollment> findById(long id) {
        return enrollments.stream()
                .filter(c -> c.getId() == id)
                .findFirst();
    }

    @Override
    public Enrollment create(Enrollment Enrollment) {
        enrollments.add(Enrollment);
        return Enrollment;
    }

    @Override
    public Enrollment update(int id, Enrollment newData) {
        Enrollment old = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        enrollments.remove(old);

        Enrollment updated = new Enrollment(id,
                newData.getStudentName(),
                newData.getCourseId());

        enrollments.add(updated);
        return updated;
    }

    @Override
    public Enrollment deleteById(int id) {
        Enrollment old = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        enrollments.remove(old);
        return old;
    }
}
