package Controller.ItemController;

import DB.DBConnection;
import com.sun.jdi.event.StepEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ItemDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemManagementController implements ItemManagementService{

    ObservableList<ItemDetails> itemDetails = FXCollections.observableArrayList();

    @Override
    public void addItemDetails(ItemDetails details) {
        String SQL = "INSERT INTO item (ItemCode, Description, PackSize, UnitPrice, QtyOnHand) VALUES (?,?,?,?,?);";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,details.getID());
            preparedStatement.setObject(2,details.getDescription());
            preparedStatement.setObject(3,details.getPackSize());
            preparedStatement.setObject(4,details.getUnitPrice());
            preparedStatement.setObject(5,details.getQTY());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void updateItemDetails(ItemDetails details) {
        String SQL = "UPDATE item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE  ItemCode = ?;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(5,details.getID());
            preparedStatement.setObject(1,details.getDescription());
            preparedStatement.setObject(2,details.getPackSize());
            preparedStatement.setObject(3,details.getUnitPrice());
            preparedStatement.setObject(4,details.getQTY());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteItemDetails(String itenID) {
        String SQL = "UPDATE item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE  ItemCode = ?;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM item WHERE ItemCode = ?;");

            preparedStatement.setObject(1,itenID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<ItemDetails> getAllItemDetails() {
        itemDetails.clear();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                itemDetails.add(new ItemDetails(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemDetails;
    }
}
