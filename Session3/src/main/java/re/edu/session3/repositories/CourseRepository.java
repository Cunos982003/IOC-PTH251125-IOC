package re.edu.session3.repositories;

import org.springframework.stereotype.Repository;
import re.edu.session3.exception.ResourceNotFoundException;
import re.edu.session3.model.Course;

import java.util.*;

@Repository
public class CourseRepository implements IBaseRepository<Course> {

    private List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        courses.add(new Course(1, "Java Basic", "ACTIVE", 1));
        courses.add(new Course(2, "Spring Boot", "ACTIVE", 2));
    }

    public List<Course> findAll() {
        return courses;
    }

    @Override
    public Course create(Course Course) {
        courses.add(Course);
        return Course;
    }

    @Override
    public Course update(int id, Course newData) {
        Course old = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        courses.remove(old);

        Course updated = new Course(id,
                newData.getTitle(),
                newData.getStatus(),
                newData.getInstructorId());

        courses.add(updated);
        return updated;
    }

    @Override
    public Course deleteById(int id) {
        Course old = findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        courses.remove(old);
        return old;
    }

    @Override
    public Optional<Course> findById(long id){
        return courses.stream().filter(i -> i.getId() == id).findFirst();
    }
}
