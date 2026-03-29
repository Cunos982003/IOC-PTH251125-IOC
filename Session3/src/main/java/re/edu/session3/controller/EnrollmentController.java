package re.edu.session3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.session3.dto.ApiResponse;
import re.edu.session3.dto.EnrollCourseRequest;
import re.edu.session3.dto.EnrollmentDetail;
import re.edu.session3.exception.ResourceNotFoundException;
import re.edu.session3.model.Course;
import re.edu.session3.model.Enrollment;
import re.edu.session3.service.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAll() {
        List<Enrollment> data = service.getAll();

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Get enrollments successfully", data)
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable int id) {
        try {
            Enrollment enrollment = service.getById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Get enrollment successfully", enrollment)
            );

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> create(@RequestBody Enrollment enrollment) {
        Enrollment created = service.create(enrollment);

        return ResponseEntity.status(201).body(
                new ApiResponse<>(true, "Enrollment created", created)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable int id,
                                                 @RequestBody Enrollment enrollment) {
        Enrollment updated = service.update(id, enrollment);

        if (updated == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, "Enrollment not found", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Enrollment updated", updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable int id) {
        Enrollment deleted = service.deleteById(id);

        if (deleted == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, "Enrollment not found", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Enrollment deleted", deleted)
        );
    }

    @PostMapping("/enroll-course")
    public ResponseEntity<ApiResponse<?>> enrollCourse(
            @RequestBody EnrollCourseRequest request) {

        try {
            EnrollmentDetail result = service.enrollCourse(request);

            return ResponseEntity.status(201)
                    .body(new ApiResponse<>(true, "Enroll success", result));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(new ApiResponse<>(false, e.getMessage(), null));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
