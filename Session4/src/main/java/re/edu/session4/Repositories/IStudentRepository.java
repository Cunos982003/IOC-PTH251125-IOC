package re.edu.session4.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.session4.model.Student;

public interface IStudentRepository extends JpaRepository<Student,Long> {
}
