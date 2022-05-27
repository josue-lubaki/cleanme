package ca.josue.cleanme.controller;

import ca.josue.cleanme.model.Reservation;
import ca.josue.cleanme.service.reservation.IReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final IReservationService reservationService;

    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation reservationSaved =  reservationService.createReservation(reservation);

        if(reservationSaved == null) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(reservationSaved);
    }
}
