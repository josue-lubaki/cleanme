package ca.josue.cleanme.model;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-05-26
 */
@Data
public class Reservation {
    private User user;
    private LocalDate date;
    private LocalTime time;
    private String comment;
}
