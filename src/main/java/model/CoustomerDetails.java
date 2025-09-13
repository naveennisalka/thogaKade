package model;

import lombok.*;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CoustomerDetails {
    private String ID;
    private String title;
    private String Name;
    private LocalDate DOB;
    private double salary;
    private String address;
    private String city;
    private String province;
    private int postalCode;

}
