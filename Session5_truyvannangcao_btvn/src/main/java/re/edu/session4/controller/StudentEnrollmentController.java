package re.edu.session4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import re.edu.session4.dto.ApiResponse;
import re.edu.session4.service.StudentEnrollmentService;

@RestController
@RequestMapping("api/v1/students-enrollments")
public class StudentEnrollmentController {

    private final StudentEnrollmentService enrollmentService;

    public StudentEnrollmentController(StudentEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> enroll(@RequestParam Long studentId,
                                                    @RequestParam Long courseId) {

        enrollmentService.enrollStudent(studentId, courseId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Enrollment success", null));
    }
}
