package ca.josue.cleanme.service.reservation;

import ca.josue.cleanme.model.Reservation;
import ca.josue.cleanme.service.mail.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-05-26
 */
@Slf4j@Service
public class ReservationService implements IReservationService {

    private final IMailService mailService;

    public ReservationService(IMailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        try {
            mailService.notificationClientSavedReservation(reservation);
            mailService.notificationAdminNewReservation(reservation);
            return reservation;
        } catch (Exception e){
            log.error("Error sending email", e);
        }

        return null;
    }
}
