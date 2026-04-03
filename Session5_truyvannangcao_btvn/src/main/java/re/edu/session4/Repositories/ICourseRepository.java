package re.edu.session4.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.edu.session4.dto.CourseResponseV2;
import re.edu.session4.model.Course;
import re.edu.session4.model.CourseStatus;

import java.util.List;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM  Course c WHERE c.status= :status")
    Page<Course> findAllByStatus(@Param("status")CourseStatus status, Pageable pageable);

    @Query("""
SELECT new re.edu.session4.dto.CourseResponseV2(
c.id,
c.title,
c.status)
FROM Course c WHERE c.status = :status
""")
    Page<CourseResponseV2> findAllByStatusV2(
            @Param("status") CourseStatus status,
            Pageable pageable
    );
}
