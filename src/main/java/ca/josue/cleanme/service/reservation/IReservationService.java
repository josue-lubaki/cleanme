package ca.josue.cleanme.service.reservation;

import ca.josue.cleanme.model.Reservation;
import org.springframework.stereotype.Service;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-05-26
 */
@Service
public interface IReservationService {
    // create a new reservation
    Reservation createReservation(Reservation reservation);
}
