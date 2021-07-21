package poojan.testexam.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//This is a test message.
@Getter
@Setter
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
}
