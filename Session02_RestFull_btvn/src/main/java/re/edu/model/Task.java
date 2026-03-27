package re.edu.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Integer id;
    private String title;
    private String description;
    private String priority;
    private int assignedTo; // userId
}
