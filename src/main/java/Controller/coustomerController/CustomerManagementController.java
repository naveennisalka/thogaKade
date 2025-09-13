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
    public void addCoustomerDetails(CoustomerDetails details)  {

        String SQl = "INSERT INTO Customer (CustID, CustTitle, CustName, DOB, salary, CustAddress, City, Province, PostalCode) VALUES (?,?,?,?,?,?,?,?,?);";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQl);

            preparedStatement.setObject(1,details.getID());
            preparedStatement.setObject(2,details.getTitle());
            preparedStatement.setObject(3,details.getName());
            preparedStatement.setObject(4,details.getDOB());
            preparedStatement.setObject(5,details.getSalary());
            preparedStatement.setObject(6,details.getAddress());
            preparedStatement.setObject(7,details.getCity());
            preparedStatement.setObject(8,details.getProvince());
            preparedStatement.setObject(9,details.getPostalCode());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCoustomerDetails(CoustomerDetails details) {
        String SQL = "UPDATE Customer SET CustTitle=?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,details.getTitle());
            preparedStatement.setObject(2,details.getName());
            preparedStatement.setObject(3,details.getDOB());
            preparedStatement.setObject(4,details.getSalary());
            preparedStatement.setObject(5,details.getAddress());
            preparedStatement.setObject(6,details.getCity());
            preparedStatement.setObject(7,details.getProvince());
            preparedStatement.setObject(8,details.getPostalCode());
            preparedStatement.setObject(9,details.getID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCoustomeDetails(String customerID) {

        String SQL = "DELETE FROM Customer WHERE CustID=?;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,customerID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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
        return coustomerDetails;
    }
}

