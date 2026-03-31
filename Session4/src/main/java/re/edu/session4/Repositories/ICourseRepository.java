package re.edu.session4.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.session4.model.Course;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {
}
