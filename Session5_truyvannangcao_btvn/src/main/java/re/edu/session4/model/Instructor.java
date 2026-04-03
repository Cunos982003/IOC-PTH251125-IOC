package re.edu.session4.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // khi sử dụng JPA tải các entity
    // Cơ chế load : LAZY(lười) và EAGER(tức thì)
    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER)
    private List<Course> courses;


}
