package Controller.coustomerController;

import javafx.collections.ObservableList;
import model.CoustomerDetails;

public interface CustomerManagementService {
    void addCoustomerDetails(CoustomerDetails details);
    void updateCoustomerDetails(CoustomerDetails details);
    void deleteCoustomeDetails(String customerID);
    ObservableList<CoustomerDetails> getAllCoustomerDetails();
}
