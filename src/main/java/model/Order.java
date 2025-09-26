package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private String orderID;
    private LocalDate orderDate;
    private String customerID;


    //private ArrayList<OrderDetails> details = new ArrayList<>();
    //private ObservableList<OrderDetails> details = FXCollections.observableArrayList();


    // Method to add detail: public void addDetail(OrderDetail detail) { details.add(detail); }
}


