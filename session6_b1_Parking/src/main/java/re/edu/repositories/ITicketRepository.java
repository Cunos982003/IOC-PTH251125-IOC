package re.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import re.edu.model.ParkingTicket;

import java.util.Optional;

public interface ITicketRepository extends JpaRepository<ParkingTicket,Long> {
    @Query("""
        SELECT t FROM ParkingTicket t
        WHERE t.vehicle.id = :vehicleId
        AND t.checkOutTime IS NULL
        ORDER BY t.checkInTime DESC
        LIMIT 1
    """)
    Optional<ParkingTicket> findActiveTicket(@Param("vehicleId") Long vehicleId);
}
