package re.edu.session4.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder // tạo nhanh đối tượng
public class InstructorDto {
    private Long id;
    private String name;
    private String email;
    private List<String> courses;
}