package re.edu.session4.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class InstructorCreateRequest {
        private String name;
        private String email;
}
