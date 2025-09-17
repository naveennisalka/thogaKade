package Controller.OrderController;

import javafx.collections.ObservableList;
import model.ItemDetails;
import model.Order;

public interface OrderManagementService {
    void addItemToOrder();
    void placeOrder();
    void viewHistory();
    void deleteOrder();
    void updateOrder();
    ObservableList<String> getAllCoustomerID();
    ObservableList<String> getAllItemID();
    String getCoustomerName(String custID);
    ItemDetails getItemDetails(String itemID);

}
