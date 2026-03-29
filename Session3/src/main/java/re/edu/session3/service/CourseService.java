package re.edu.session3.service;

import org.springframework.stereotype.Service;
import re.edu.session3.exception.ResourceNotFoundException;
import re.edu.session3.model.Course;
import re.edu.session3.repositories.CourseRepository;

import java.util.List;

@Service
public class CourseService implements IBaseService<Course> {
    private final CourseRepository courseRepo;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepo=courseRepository;
    }

    public List<Course> getAllCourses()
    {
        return courseRepo.findAll();
    }

    @Override
    public List<Course> getAll() {
        return  courseRepo.findAll();
    }

    public Course getById(long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public Course create(Course Course) {
        return  courseRepo.create(Course);
    }

    @Override
    public Course update(int id, Course Course) {
        return  courseRepo.update(id, Course);
    }

    @Override
    public Course deleteById(int id) {
        return  courseRepo.deleteById(id);
    }
}
