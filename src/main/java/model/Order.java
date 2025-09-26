package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private String orderID;
    private LocalDate orderDate;
    private String customerID;

}


