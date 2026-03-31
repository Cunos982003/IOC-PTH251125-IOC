package re.edu.session4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 50, nullable = false, unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name= "status" ,nullable = false)
    private CourseStatus status;

   @ManyToOne
   @JoinColumn(name = "instructor_id",  nullable = false)
   private Instructor instructor;


}
