package ca.josue.cleanme.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import ca.josue.cleanme.model.Address;
import ca.josue.cleanme.model.Reservation;
import ca.josue.cleanme.model.User;

import java.time.LocalDate;
import java.time.LocalTime;

import ca.josue.cleanme.service.mail.IMailService;
import ca.josue.cleanme.service.reservation.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReservationService.class})
@ExtendWith(SpringExtension.class)
class ReservationServiceTest {
    @MockBean
    private IMailService iMailService;

    @Autowired
    private ReservationService reservationService;

    /**
     * Method under test: {@link ReservationService#createReservation(Reservation)}
     */
    @Test
    void testCreateReservation() {
        doNothing().when(this.iMailService).notificationAdminNewReservation(any());
        doNothing().when(this.iMailService).notificationClientSavedReservation(any());

        Address address = new Address();
        address.setApartment("Apartment");
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreet("Street");
        address.setZip("21654");

        User user = new User();
        user.setAddress(address);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setPhone("4105551212");

        Reservation reservation = new Reservation();
        reservation.setComment("Comment");
        reservation.setDate(LocalDate.ofEpochDay(1L));
        reservation.setTime(LocalTime.of(1, 1));
        reservation.setUser(user);
        assertSame(reservation, this.reservationService.createReservation(reservation));
        verify(this.iMailService).notificationAdminNewReservation(any());
        verify(this.iMailService).notificationClientSavedReservation(any());
    }
}

