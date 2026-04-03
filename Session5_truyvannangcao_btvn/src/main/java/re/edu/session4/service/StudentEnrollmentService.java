package re.edu.session4.service;

import org.springframework.stereotype.Service;
import re.edu.session4.Repositories.ICourseRepository;
import re.edu.session4.Repositories.IStudentEnrollmentRepository;
import re.edu.session4.Repositories.IStudentRepository;
import re.edu.session4.model.Course;
import re.edu.session4.model.Student;
import re.edu.session4.model.StudentEnrollment;


@Service
public class StudentEnrollmentService {

    private final IStudentRepository studentRepository;
    private final ICourseRepository courseRepository;
    private final IStudentEnrollmentRepository enrollmentRepository;

    public StudentEnrollmentService(IStudentRepository studentRepository,
                                    ICourseRepository courseRepository,
                                    IStudentEnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public void enrollStudent(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        enrollmentRepository.save(enrollment);
    }
}