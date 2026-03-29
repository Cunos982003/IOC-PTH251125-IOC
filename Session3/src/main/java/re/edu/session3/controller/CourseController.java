package re.edu.session3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import re.edu.session3.dto.ApiResponse;
import re.edu.session3.exception.ResourceNotFoundException;
import re.edu.session3.model.Course;
import re.edu.session3.model.Instructor;
import re.edu.session3.service.CourseService;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Get courses successfully", courseService.getAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable int id) {
        try {
            Course course = courseService.getById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Get course successfully", course)
            );

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course course) {
        Course created = courseService.create(course);

        return ResponseEntity.status(201).body(
                new ApiResponse<>(true, "Course created", created)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable int id,
                                                 @RequestBody Course  course) {
        Course updated = courseService.update(id, course);

        if (updated == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, "Course not found", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Course updated", updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable int id) {
        Course deleted = courseService.deleteById(id);

        if (deleted == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, "Course not found", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Course deleted", deleted)
        );
    }
}
