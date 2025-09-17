package model;


import lombok.*;
@Data
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
