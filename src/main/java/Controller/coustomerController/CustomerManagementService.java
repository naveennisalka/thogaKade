package Controller.coustomerController;

import javafx.collections.ObservableList;
import model.CoustomerDetails;

import java.sql.SQLException;

public interface CustomerManagementService {
    void addCoustomerDetails(CoustomerDetails details) throws SQLException;
    void updateCoustomerDetails(CoustomerDetails details);
    void deleteCoustomeDetails(String customerID);
    ObservableList<CoustomerDetails> getAllCoustomerDetails();
}
