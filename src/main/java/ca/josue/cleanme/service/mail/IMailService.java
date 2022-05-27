package ca.josue.cleanme.service.mail;

import ca.josue.cleanme.model.Reservation;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-05-26
 */
public interface IMailService {

    @Async
    void notificationClientSavedReservation(Reservation reservation);

    @Async
    void notificationAdminNewReservation(Reservation reservation);

}
