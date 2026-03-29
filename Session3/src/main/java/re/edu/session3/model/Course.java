package re.edu.session3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Course {
    private int id;
    private String title;
    private String status;
    private int instructorId;

    public Course(int id, String title, String status, int instructorId) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructorId = instructorId;
    }
}
