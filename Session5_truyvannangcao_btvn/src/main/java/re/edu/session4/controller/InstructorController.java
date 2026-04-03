package re.edu.session4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.session4.dto.ApiResponse;
import re.edu.session4.dto.InstructorCreateRequest;
import re.edu.session4.dto.InstructorDto;
import re.edu.session4.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }
    @GetMapping("/test")
    public List<InstructorDto> getInstructors() {
        return instructorService.getInstructors();
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody InstructorCreateRequest req) {
        instructorService.createInstructor(req);
        return ResponseEntity.ok(new ApiResponse<>(true, "Instructor created", null));
    }

    @GetMapping
    public ResponseEntity<?> getInstructorByPaginate(
        @RequestParam(defaultValue = "0") int pageNumber,
        @RequestParam(defaultValue = "5") int pageSize
    ){
        return new ResponseEntity<>(instructorService.getInstructorsByPaginate(pageNumber, pageSize), HttpStatus.OK);
    }

}
