package ca.josue.cleanme.model;

import lombok.Data;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-05-26
 */
@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
