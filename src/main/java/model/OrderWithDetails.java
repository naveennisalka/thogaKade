package model;


import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderWithDetails{
    private String orderID;
    private LocalDate orderDate;
    private String cutID;
    private String itemCode;
    private int orderQTY;
    private int discount;

}

