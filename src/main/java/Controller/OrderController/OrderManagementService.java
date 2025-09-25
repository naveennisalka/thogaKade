package Controller.OrderController;

import javafx.collections.ObservableList;
import model.ItemDetails;
import model.ItemWithDetails;
import model.Order;
import model.OrderWithDetails;

public interface OrderManagementService {
    void addItemToOrder();
    void placeOrder(Order order);
    void viewHistory();
    void deleteOrder();
    void updateOrder();
    ObservableList<String> getAllCoustomerID();
    ObservableList<String> getAllItemID();
    String getCoustomerName(String custID);
    ItemDetails getItemDetails(String itemID);
    String getOrderID();
    ObservableList<OrderWithDetails> getOrderbyID(String orderID);
    ObservableList<ItemWithDetails> getOrderedItemDetails(String orderID);
    Order getOrderDetails(String OrderID);



}
