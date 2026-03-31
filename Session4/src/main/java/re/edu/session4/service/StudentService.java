package re.edu.session4.service;

import org.springframework.stereotype.Service;
import re.edu.session4.Repositories.IStudentRepository;
import re.edu.session4.dto.StudentCreateRequest;
import re.edu.session4.model.Student;

@Service
public class StudentService {

    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(StudentCreateRequest req) {
        Student student = new Student();
        student.setName(req.getName());
        student.setEmail(req.getEmail());

        studentRepository.save(student);
    }
}
