package re.edu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import re.edu.dto.VehicleResponse;
import re.edu.model.Vehicle;

public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("""
    SELECT new re.edu.dto.VehicleResponse(
        v.id, v.licensePlate, v.color, v.type
    )
    FROM Vehicle v
    WHERE (:keyword IS NULL OR LOWER(v.licensePlate) LIKE :keyword)
""")
    Page<VehicleResponse> findAllByKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
