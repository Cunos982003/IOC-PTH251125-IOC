package re.edu.session3.repositories;

import org.springframework.stereotype.Repository;
import re.edu.session3.exception.ResourceNotFoundException;
import re.edu.session3.model.Instructor;

import java.util.*;

@Repository
public class InstructorRepository implements IBaseRepository<Instructor> {

    private final List<Instructor> instructors = new ArrayList<>();

    public InstructorRepository() {
        instructors.add(new Instructor(1, "Nguyen Van A", "a@gmail.com"));
        instructors.add(new Instructor(2, "Tran Thi B", "b@gmail.com"));
    }

    public List<Instructor> findAll() {
        return instructors;
    }

    @Override
    public Optional<Instructor> findById(long id) {
        return instructors.stream().filter(i -> i.getId() == id).findFirst();
    }

    @Override
    public Instructor create(Instructor instructor) {
        instructors.add(instructor);
        return instructor;
    }

    @Override
    public Instructor update(int id, Instructor newData) {
        Instructor old = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        instructors.remove(old);

        Instructor updated = new Instructor(id,
                newData.getName(),
                newData.getEmail());

        instructors.add(updated);
        return updated;
    }

    @Override
    public Instructor deleteById(int id) {
        Instructor old = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        instructors.remove(old);
        return old;
    }
}