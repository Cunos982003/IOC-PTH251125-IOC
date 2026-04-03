package re.edu.session4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.session4.model.CourseStatus;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;
}
