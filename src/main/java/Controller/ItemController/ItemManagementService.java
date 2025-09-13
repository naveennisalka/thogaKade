package Controller.ItemController;

import javafx.collections.ObservableList;
import model.ItemDetails;

public interface ItemManagementService {
    void addItemDetails(ItemDetails details);

    void updateItemDetails(ItemDetails details);

    void deleteItemDetails(String itenID);

    ObservableList<ItemDetails> getAllItemDetails();
}