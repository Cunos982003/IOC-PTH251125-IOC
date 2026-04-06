package re.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import re.edu.dto.ZoneStatisticsResponse;
import re.edu.model.Zone;

import java.util.List;

public interface IZoneRepository extends JpaRepository<Zone,Long> {
    @Query("""
    SELECT new re.edu.dto.ZoneStatisticsResponse(
        z.id,
        z.name,
        z.capacity,
        z.occupiedSpots,
        (z.capacity - z.occupiedSpots)
    )
    FROM Zone z
""")
    List<ZoneStatisticsResponse> getZoneStatsV2();
}
