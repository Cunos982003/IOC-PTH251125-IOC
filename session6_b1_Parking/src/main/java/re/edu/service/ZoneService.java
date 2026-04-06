package re.edu.service;

import org.springframework.stereotype.Service;
import re.edu.dto.ZoneStatisticsResponse;
import re.edu.model.Zone;
import re.edu.repositories.IZoneRepository;

import java.util.List;

@Service
public class ZoneService {
    private IZoneRepository zoneRepo;
    public ZoneService(IZoneRepository zoneRepo) {
        this.zoneRepo = zoneRepo;
    }
    public List<ZoneStatisticsResponse> getZoneStatsV1() {

        long start = System.currentTimeMillis();

        List<Zone> zones = zoneRepo.findAll();

        List<ZoneStatisticsResponse> result = zones.stream()
                .map(z -> new ZoneStatisticsResponse(
                        z.getId(),
                        z.getName(),
                        z.getCapacity(),
                        z.getOccupiedSpots(),
                        z.getCapacity() - z.getOccupiedSpots()
                ))
                .toList();

        long end = System.currentTimeMillis();
        System.out.println("V1 Execution time: " + (end - start) + " ms");

        return result;
    }

    public List<ZoneStatisticsResponse> getZoneStatsV2() {

        long start = System.currentTimeMillis();

        List<ZoneStatisticsResponse> result = zoneRepo.getZoneStatsV2();

        long end = System.currentTimeMillis();
        System.out.println("V2 Execution time: " + (end - start) + " ms");

        return result;
    }
}
