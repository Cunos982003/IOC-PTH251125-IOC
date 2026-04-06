package re.edu.dto;

import lombok.Getter;
import lombok.Setter;
import re.edu.model.VehicleType;

@Getter
@Setter
public class VehicleCreateRequest {
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;
}
