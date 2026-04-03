package re.edu.session4.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.session4.model.StudentEnrollment;

public interface IStudentEnrollmentRepository extends JpaRepository<StudentEnrollment,Long> {
}
