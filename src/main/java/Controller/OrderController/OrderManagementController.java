package Controller.OrderController;

import DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ItemDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManagementController implements OrderManagementService{

    @Override
    public void addItemToOrder() {

    }

    @Override
    public void placeOrder() {

    }

    @Override
    public void viewHistory() {

    }

    @Override
    public void deleteOrder() {

    }

    @Override
    public void updateOrder() {

    }

    @Override
    public ObservableList<String> getAllCoustomerID() {
        ObservableList<String> custID = FXCollections.observableArrayList();
        String SQL = "SELECT CustID FROM customer;";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                custID.add(resultSet.getString("CustID"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return custID;
    }



    @Override
    public ObservableList<String> getAllItemID() {
        ObservableList<String> ItemID = FXCollections.observableArrayList();
        String SQL = "SELECT ItemCode FROM item;";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ItemID.add(resultSet.getString("ItemCode"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ItemID;
    }

    @Override
    public String getCoustomerName(String custID) {
        String SQL = "SELECT CustTitle,CustName from customer where CustID = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,custID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String custTitle = resultSet.getString("CustTitle");
                String custName = resultSet.getString("CustName");
                return custTitle + " " + custName;
            } else {
                // Return null or a default message if no customer is found
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemDetails getItemDetails(String itemID) {
        //ObservableList<ItemDetails> itemDetails = FXCollections.observableArrayList();
        String SQL = "SELECT ItemCode, Description, PackSize, UnitPrice, QtyOnHand FROM item WHERE ItemCode = ? AND QtyOnHand > 0;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, itemID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new ItemDetails(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
