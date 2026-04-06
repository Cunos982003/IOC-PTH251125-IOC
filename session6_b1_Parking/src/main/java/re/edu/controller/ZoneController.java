package re.edu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.service.ZoneService;

@RestController
@RequestMapping("/api")
public class ZoneController {
    private final ZoneService zoneService;
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }
    @GetMapping("/v1/zones/stats")
    public ResponseEntity<?> getStatsV1() {
        return ResponseEntity.ok(zoneService.getZoneStatsV1());
    }

    // V2
    @GetMapping("/v2/zones/stats")
    public ResponseEntity<?> getStatsV2() {
        return ResponseEntity.ok(zoneService.getZoneStatsV2());
    }
}
