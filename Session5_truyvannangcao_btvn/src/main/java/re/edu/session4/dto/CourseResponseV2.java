package re.edu.session4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import re.edu.session4.model.CourseStatus;

@AllArgsConstructor
@Getter
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
}
