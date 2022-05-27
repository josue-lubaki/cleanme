package ca.josue.cleanme.model;

import lombok.Data;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-05-26
 */
@Data
public class Address {
    private String apartment;
    private String street;
    private String city;
    private String state;
    private String zip;
}
