package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private String orderID;
    private String orderDate;
    private String customerID;
    private ArrayList<OrderDetails> details = new ArrayList<>();


    // Method to add detail: public void addDetail(OrderDetail detail) { details.add(detail); }
}


