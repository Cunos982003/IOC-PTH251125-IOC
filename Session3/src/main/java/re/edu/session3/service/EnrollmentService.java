package re.edu.session3.service;

import org.springframework.stereotype.Service;
import re.edu.session3.dto.EnrollCourseRequest;
import re.edu.session3.dto.EnrollmentDetail;
import re.edu.session3.exception.ResourceNotFoundException;
import re.edu.session3.model.Course;
import re.edu.session3.model.Enrollment;
import re.edu.session3.repositories.CourseRepository;
import re.edu.session3.repositories.EnrollmentRepository;
import re.edu.session3.repositories.InstructorRepository;

import java.util.List;
import java.util.Random;

@Service
public class EnrollmentService implements IBaseService<Enrollment> {

    private final EnrollmentRepository enrollmentRepo;
    private final CourseRepository courseRepo;
    private final InstructorRepository instructorRepo;

    public EnrollmentService(EnrollmentRepository enrollmentRepo,
                             CourseRepository courseRepo,
                             InstructorRepository instructorRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.courseRepo = courseRepo;
        this.instructorRepo = instructorRepo;
    }

    @Override
    public List<Enrollment> getAll() {
        return enrollmentRepo.findAll();
    }

    public Enrollment getById(long id) {
        return enrollmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
    }

    @Override
    public Enrollment create(Enrollment Enrollment) {
        return enrollmentRepo.create(Enrollment);
    }

    @Override
    public Enrollment update(int id, Enrollment Enrollment) {
        return enrollmentRepo.update(id, Enrollment);
    }

    @Override
    public Enrollment deleteById(int id) {
        return enrollmentRepo.deleteById(id);
    }

    public EnrollmentDetail enrollCourse(EnrollCourseRequest request) {

        // 1. Check Course tồn tại
        Course course = courseRepo.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // 2. Check Course ACTIVE
        if (!"ACTIVE".equalsIgnoreCase(course.getStatus())) {
            throw new IllegalArgumentException("Course is not ACTIVE");
        }

        // 3. Check Instructor tồn tại
        instructorRepo.findById(course.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

        // 4. Tạo Enrollment
        Enrollment enrollment = new Enrollment(
                new Random().nextInt(1000), // fake id
                request.getStudentName(),
                course.getId()
        );

        enrollmentRepo.create(enrollment);

        // 5. Trả DTO
        return new EnrollmentDetail(
                enrollment.getId(),
                enrollment.getStudentName(),
                course
        );
    }
}