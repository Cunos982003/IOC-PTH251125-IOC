package re.edu.session3.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Instructor {
    private int id;
    private String name;
    private String email;

    public Instructor(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
