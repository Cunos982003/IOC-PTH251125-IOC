package re.edu.session4.service;

import org.springframework.stereotype.Service;
import re.edu.session4.Repositories.ICourseRepository;
import re.edu.session4.Repositories.IInstructorRepository;
import re.edu.session4.dto.CourseCreateRequest;
import re.edu.session4.dto.CourseInstructorResponse;
import re.edu.session4.dto.CourseReponse;
import re.edu.session4.dto.CourseUpdateRequest;
import re.edu.session4.model.Course;
import re.edu.session4.model.Instructor;

import java.util.List;

@Service
public class CourseService {

    private final ICourseRepository courseRepository;
    private final IInstructorRepository instructorRepository;

    public CourseService(ICourseRepository courseRepository,
                         IInstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public void createCourse(CourseCreateRequest req) {

        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = new Course();
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    public void updateCourse(Long id, CourseUpdateRequest req) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Instructor instructor = instructorRepository.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    public List<CourseReponse> getAllCourses() {

        List<Course> courses = courseRepository.findAll();

        return courses.stream().map(course -> {

            CourseInstructorResponse instructorDTO =
                    new CourseInstructorResponse(
                            course.getInstructor().getId(),
                            course.getInstructor().getName()
                    );

            return new CourseReponse(
                    course.getId(),
                    course.getTitle(),
                    course.getStatus(),
                    instructorDTO
            );

        }).toList();
    }
}
