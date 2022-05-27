package ca.josue.cleanme.service.mail;

import ca.josue.cleanme.model.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-05-26
 */
@Slf4j
@Service
public class MailServiceImpl implements IMailService{
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public MailServiceImpl(JavaMailSender javaMailSender,
                           SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void notificationClientSavedReservation(Reservation reservation) {
        String emailClient = reservation.getUser().getEmail();
        Context context = getContext(reservation);

        String body = templateEngine.process("mail/client-saved-reservation", context);
        sendPlainTextMessage(emailClient, "Confirmation de votre Réservation", body);
    }

    @Override
    public void notificationAdminNewReservation(Reservation reservation) {
        Context context = getContext(reservation);
        context.setVariable("apartment", reservation.getUser().getAddress().getApartment());
        context.setVariable("street", reservation.getUser().getAddress().getStreet());
        context.setVariable("city", reservation.getUser().getAddress().getCity());
        context.setVariable("zip", reservation.getUser().getAddress().getZip());
        context.setVariable("phone", reservation.getUser().getPhone());
        context.setVariable("email", reservation.getUser().getEmail());
        context.setVariable("state", reservation.getUser().getAddress().getState());

        String body = templateEngine.process("mail/admin-new-reservation", context);
        sendPlainTextMessage(senderEmail, "Nouvelle Réservation", body);
    }

    private Context getContext(Reservation reservation) {
        String fullNameClient = reservation.getUser().getFullName();
        String time = formatTime(reservation);
        String date = formatDate(reservation);

        Context context = new Context();
        context.setVariable("fullname", fullNameClient);
        context.setVariable("date", date);
        context.setVariable("time", time);
        context.setVariable("comment", reservation.getComment());
        return context;
    }

    /**
     * Method to format the date of the reservation
     * @param reservation the reservation to format the date
     * @return the date formatted (dd/MM/yyyy)
     * */
    private String formatDate(Reservation reservation) {
        String day = reservation.getDate().getDayOfMonth() < 10 ?
                ("0" + reservation.getDate().getDayOfMonth()) :
                String.valueOf(reservation.getDate().getDayOfMonth());

        String month = reservation.getDate().getMonthValue() < 10 ?
                ("0" + reservation.getDate().getMonthValue()) :
                String.valueOf(reservation.getDate().getMonthValue());

        // formatter date en dd/MM/yyyy
        return day + "/" + month + "/" + reservation.getDate().getYear();
    }

    /**
     * method to format time reservation
     * @param reservation reservation to format time
     * @return String representation of time (HH:mm)
     * */
    private String formatTime(Reservation reservation) {
        // formatter date en HH:mm
        String hours = reservation.getTime().getHour() < 10 ?
                ("0" + reservation.getTime().getHour()) :
                String.valueOf(reservation.getTime().getHour());

        String minutes = reservation.getTime().getMinute() < 10 ?
                ("0" + reservation.getTime().getMinute()) :
                String.valueOf(reservation.getTime().getMinute());

        return hours + ":" + minutes;
    }

    /**
     * Method to send a plain text message
     * @param to the email address of the recipient of the message (e.g. "josuelubaki@gmail.com")
     * @param subject the subject of the message
     *                (e.g. "Réinitialisation de mot de passe")
     * @param content the content of the message (e.g. "Your temporary password is: 12345")
     * @catch MessagingException if the message cannot be sent
     * */
    @Async
    public void sendPlainTextMessage(String to, String subject, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(senderEmail);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            // sending mimeMessage
            javaMailSender.send(mimeMessage);
            log.info("Mail sent to {}", to);
        } catch (MessagingException e) {
            log.warn("Email could not be sent to user '{}' : '{}'", to, e.getMessage());
        }
    }
}
