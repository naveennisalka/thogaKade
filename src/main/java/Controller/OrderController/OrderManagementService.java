package Controller.OrderController;

import javafx.collections.ObservableList;
import model.*;

public interface OrderManagementService {
    void placeOrder(ObservableList<TempItemDetails> addedItems, Order order,ObservableList<String> deletedItemsIDInOrder);
    void viewHistory();
    ObservableList<String> getAllCoustomerID();
    ObservableList<String> getAllItemID();
    String getCoustomerName(String custID);
    ItemDetails getItemDetails(String itemID);
    String getOrderID();
    ObservableList<OrderWithDetails> getOrderbyID(String orderID);
    ObservableList<ItemWithDetails> getOrderedItemDetails(String orderID);
    Order getOrderDetails(String OrderID);
}
