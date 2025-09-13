package Controller.coustomerController;

import DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CoustomerDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManagementController implements CustomerManagementService  {

    ObservableList<CoustomerDetails> coustomerDetails = FXCollections.observableArrayList();

    @Override
    public void addCoustomerDetails(CoustomerDetails details) {


    }

    @Override
    public void updateCoustomerDetails(CoustomerDetails details) {

    }

    @Override
    public void deleteCoustomeDetails(String customerID) {

    }

    @Override
    public ObservableList<CoustomerDetails> getAllCoustomerDetails() {
        coustomerDetails.clear();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Select * FROM customer;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                coustomerDetails.add(new CoustomerDetails(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getInt("PostalCode")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(coustomerDetails);
        return coustomerDetails;
    }
}

