package re.edu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "zones")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "occupied_spots")
    private int occupiedSpots;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<ParkingTicket> parkingTickets;
}