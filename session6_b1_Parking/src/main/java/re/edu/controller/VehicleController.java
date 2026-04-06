package re.edu.controller;

import org.springframework.web.bind.annotation.*;
import re.edu.dto.ApiResponse;
import re.edu.dto.PageResponse;
import re.edu.dto.VehicleCreateRequest;
import re.edu.dto.VehicleResponse;
import re.edu.service.VehicleService;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @PostMapping
    public ApiResponse<Void> create(@RequestBody VehicleCreateRequest request) {
        vehicleService.createVehicle(request);
        return new ApiResponse<>(true, "Created Sucessfully", null);

    }

    @GetMapping
    public ApiResponse<PageResponse<VehicleResponse>> getAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String sortBy,
        @RequestParam(required = false) String direction,
        @RequestParam(required = false) String keyword
    ){
        PageResponse<VehicleResponse> data = vehicleService.getPagedVehicles(page, size, sortBy, direction, keyword);
        return new ApiResponse<>(true, "Sucess", data);
    }


}
