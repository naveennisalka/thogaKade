package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemWithDetails {
    private String itemCode;
    private int OrderQTY;
    private int Discount;
    private String Description;
    private String PackSize;
    private double UnitPrice;
}
