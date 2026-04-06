package re.edu.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import re.edu.model.VehicleType;

@Getter
@Setter
@Builder

public class VehicleResponse {
    private Long id;
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;

}
