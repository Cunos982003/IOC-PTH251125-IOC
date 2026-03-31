package re.edu.session4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.session4.dto.ApiResponse;
import re.edu.session4.dto.CourseCreateRequest;
import re.edu.session4.dto.CourseReponse;
import re.edu.session4.dto.CourseUpdateRequest;
import re.edu.session4.model.Course;
import re.edu.session4.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseReponse>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Get courses successfully", courseService.getAllCourses())
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody CourseCreateRequest req) {
        courseService.createCourse(req);
        return ResponseEntity.ok(new ApiResponse<>(true, "Course created", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id,
                                                    @RequestBody CourseUpdateRequest req) {
        courseService.updateCourse(id, req);
        return ResponseEntity.ok(new ApiResponse<>(true, "Course updated", null));
    }
}
