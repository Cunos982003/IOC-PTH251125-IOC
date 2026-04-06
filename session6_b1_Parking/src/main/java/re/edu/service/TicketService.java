package re.edu.service;

import lombok.Singular;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import re.edu.dto.ApiResponse;
import re.edu.dto.PageResponse;
import re.edu.dto.TicketResponse;
import re.edu.dto.TicketSummaryResponse;
import re.edu.model.ParkingTicket;
import re.edu.repositories.IParkingTicketRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    private final IParkingTicketRepository ticketRepo;

    public TicketService(IParkingTicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    private TicketResponse mapToResponse(ParkingTicket t) {
        return new TicketResponse(
                t.getId(),
                t.getVehicle().getLicensePlate(),
                t.getZone().getName(),
                t.getCheckInTime(),
                t.getCheckOutTime()
        );
    }

    public ApiResponse<List<TicketSummaryResponse>> getTodaySummary() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().plusDays(1).atStartOfDay();
        List<TicketSummaryResponse> data =
                ticketRepo.getTodayTickets(start, end);

        return new ApiResponse<>(true, "Success", data);
    }

    public PageResponse<TicketResponse> getTicketHistory(
            String licensePlate,
            LocalDateTime fromDate,
            LocalDateTime toDate,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "checkInTime")
        );

        Page<ParkingTicket> ticketPage =
                ticketRepo.searchTickets(licensePlate, fromDate, toDate, pageable);

        List<TicketResponse> items = ticketPage.getContent().stream()
                .map(this::mapToResponse)
                .toList();

        return new PageResponse<>(
                items,
                ticketPage.getNumber(),
                ticketPage.getSize(),
                ticketPage.getTotalElements(),
                ticketPage.getTotalPages(),
                ticketPage.isLast()
        );
    }
}
