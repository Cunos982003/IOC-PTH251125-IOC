package re.edu.session3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.session3.model.Course;


@NoArgsConstructor
@Getter
@Setter
public class EnrollmentDetail {
    long id;
    String studentName;
    Course course;

    public EnrollmentDetail(long id, String studentName, Course course) {
        this.id = id;
        this.studentName = studentName;
        this.course = course;
    }
}
