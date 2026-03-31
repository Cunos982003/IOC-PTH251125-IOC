package re.edu.session4.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.session4.dto.ApiResponse;
import re.edu.session4.dto.StudentCreateRequest;
import re.edu.session4.service.StudentService;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody StudentCreateRequest req) {
        studentService.createStudent(req);
        return ResponseEntity.ok(new ApiResponse<>(true, "Student created", null));
    }
}
