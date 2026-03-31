package re.edu.session4.service;

import org.springframework.stereotype.Service;
import re.edu.session4.Repositories.IInstructorRepository;
import re.edu.session4.dto.InstructorCreateRequest;
import re.edu.session4.model.Instructor;

import java.util.List;

@Service
public class InstructorService {
    private final IInstructorRepository instructorRepository;

    public InstructorService(IInstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor findInstructorById(Long id){
        return instructorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Instructor not found") );

    }

    public List<Instructor> findAllInstructors(){
        return instructorRepository.findAll();
    }

    public Instructor createInstructor(InstructorCreateRequest req){
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());

        return instructorRepository.save(instructor);
    }

}
