package model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDetails {
    private String ID;
    private String Description;
    private String PackSize;
    private double UnitPrice;
    private int QTY;
}
