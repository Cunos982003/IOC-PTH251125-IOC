package re.edu.session4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.session4.dto.*;
import re.edu.session4.model.CourseStatus;
import re.edu.session4.service.CourseService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses() {
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

//    @GetMapping
//    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getCourses(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(required = false) String sortBy,
//            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
//            @RequestParam(defaultValue = "ACTIVE") CourseStatus status
//    ) {
//
//        PageResponse<CourseResponse> result =
//                courseService.getPagedCoursesByStatus(page, size, sortBy, direction,status);
//
//        return ResponseEntity.ok(
//                new ApiResponse<>(true, "Success", result)
//        );
//    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseV2>>> getCoursesV2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(defaultValue = "Registered") CourseStatus status
    ) {

        Page<CourseResponseV2> dtoPage =
                courseService.getPagedCoursesV2(page, size, sortBy, direction, status);

        PageResponse<CourseResponseV2> response = new PageResponse<>(
                dtoPage.getContent(),
                dtoPage.getNumber(),
                dtoPage.getSize(),
                dtoPage.getTotalElements(),
                dtoPage.getTotalPages(),
                dtoPage.isLast()
        );

        return ResponseEntity.ok(
                new ApiResponse<>(true,"Success", response)
        );
    }


}
