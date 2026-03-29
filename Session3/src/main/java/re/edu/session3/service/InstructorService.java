package re.edu.session3.service;

import org.springframework.stereotype.Service;
import re.edu.session3.exception.ResourceNotFoundException;
import re.edu.session3.model.Course;
import re.edu.session3.model.Instructor;
import re.edu.session3.repositories.InstructorRepository;

import java.util.List;

@Service
public class InstructorService implements IBaseService<Instructor>{

    private final InstructorRepository instructorRepo;

    public InstructorService(InstructorRepository repository) {
        this.instructorRepo = repository;
    }

    @Override
    public List<Instructor> getAll() {
        return instructorRepo.findAll();
    }

    public Instructor getById(long id) {
        return instructorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));
    }

    @Override
    public Instructor create(Instructor instructor) {
        return instructorRepo.create(instructor);
    }

    @Override
    public Instructor update(int id, Instructor instructor) {
        return instructorRepo.update(id, instructor);
    }

    @Override
    public Instructor deleteById(int id) {
        return instructorRepo.deleteById(id);
    }


}
