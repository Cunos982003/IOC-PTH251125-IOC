package re.edu.session3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.session3.dto.ApiResponse;
import re.edu.session3.exception.ResourceNotFoundException;
import re.edu.session3.model.Enrollment;
import re.edu.session3.model.Instructor;
import re.edu.session3.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("api/v1/instructors")
public class InstructorController {

    private final InstructorService service;

    public InstructorController(InstructorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAll() {
        List<Instructor> data = service.getAll();

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Get instructors successfully", data)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable int id) {
        try {
            Instructor instructor = service.getById(id);

            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Get enrollment successfully", instructor)
            );

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> create(@RequestBody Instructor instructor) {
        Instructor created = service.create(instructor);

        return ResponseEntity.status(201).body(
                new ApiResponse<>(true, "Instructor created", created)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable int id,
                                                 @RequestBody Instructor instructor) {
        Instructor updated = service.update(id, instructor);

        if (updated == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, "Instructor not found", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Instructor updated", updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable int id) {
        Instructor deleted = service.deleteById(id);

        if (deleted == null) {
            return ResponseEntity.status(404).body(
                    new ApiResponse<>(false, "Instructor not found", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Instructor deleted", deleted)
        );
    }
}
