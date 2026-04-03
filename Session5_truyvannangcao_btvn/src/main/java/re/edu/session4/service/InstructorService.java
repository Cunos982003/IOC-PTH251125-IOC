package re.edu.session4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import re.edu.session4.Repositories.IInstructorRepository;
import re.edu.session4.dto.InstructorCreateRequest;
import re.edu.session4.dto.InstructorDto;
import re.edu.session4.model.Course;
import re.edu.session4.model.Instructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final IInstructorRepository instructorRepository;

    public Instructor findInstructorById(Long id){
        return instructorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Instructor not found") );

    }

    public List<InstructorDto> getInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructors.stream()
                .map(this::mapToDto).toList();
    }

    private InstructorDto mapToDto(Instructor entity){
        return InstructorDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .courses(entity.getCourses().stream().map(Course::getTitle).toList())
                .build();
    }


    public Instructor createInstructor(InstructorCreateRequest req){
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());

        return instructorRepository.save(instructor);
    }

    public List<Instructor> getInstructorsByPaginate(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return instructorRepository.findAll(pageable).getContent();
    }

}
