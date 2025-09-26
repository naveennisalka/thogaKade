package Controller.OrderController;

import DB.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.Introspector;
import java.sql.*;
import java.time.LocalDate;

public class OrderManagementController implements OrderManagementService{
    ObservableList<ItemDetails> itemDetails = FXCollections.observableArrayList();


    public boolean isOrderIDExist(String orderID) {
        String SQL = "SELECT 1 FROM orders WHERE OrderID = ? LIMIT 1";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL)) {

            ps.setString(1, orderID);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void placeOrder(ObservableList<TempItemDetails> addedItems, Order order,ObservableList<String> deletedItemsIDInOrder) {
        String newOrderSQL = "INSERT INTO orders (OrderID, OrderDate, CustID) VALUES (?, ?, ?)";
        String addItemToOrderSQL = "INSERT INTO orderdetail (OrderID, ItemCode, OrderQTY, Discount) VALUES (?, ?, ?, ?)";
        String deleteItemFromOrder = "DELETE FROM orderdetail WHERE OrderID = ? AND ItemCode = ?";
        String CheckOrder = "SELECT 1 FROM orders WHERE OrderID = ? LIMIT 1";

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement psNewOrder = connection.prepareStatement(newOrderSQL);
            PreparedStatement psAddItem = connection.prepareStatement(addItemToOrderSQL);
            PreparedStatement psDltItem = connection.prepareStatement(deleteItemFromOrder);
            PreparedStatement psCheckOrder = connection.prepareStatement(CheckOrder);

            psCheckOrder.setString(1, order.getOrderID());
            try (ResultSet rs = psCheckOrder.executeQuery()) {
                boolean orderExists = rs.next();
                if (!orderExists) {
                    // Insert new order
                    psNewOrder.setString(1, order.getOrderID());
                    psNewOrder.setDate(2, Date.valueOf(order.getOrderDate()));
                    psNewOrder.setString(3, order.getCustomerID());
                    psNewOrder.executeUpdate();

                }
            }

            if(addedItems != null){
                for (TempItemDetails item : addedItems){
                    psAddItem.setString(1,order.getOrderID());
                    psAddItem.setString(2,item.getID());
                    psAddItem.setInt(3,item.getQTY());
                    psAddItem.setInt(4,item.getDiscount());
                    psAddItem.executeUpdate();
                }
            }

            if(deletedItemsIDInOrder != null){
                for (String item : deletedItemsIDInOrder){
                    psDltItem.setString(1,order.getOrderID());
                    psDltItem.setString(2,item);
                    psDltItem.executeUpdate();
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void viewHistory() {

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
    public ObservableList<ItemWithDetails> getOrderedItemDetails(String orderID){
        ObservableList<ItemWithDetails> itemWithDetails = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM orderdetail WHERE OrderID = ?";
        String SQL2 = "SELECT Description, PackSize, UnitPrice FROM item WHERE ItemCode = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1,orderID);
            PreparedStatement preparedStatement1 = connection.prepareStatement(SQL2);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                String itemCode = resultSet.getString("ItemCode");
                int OrderQTY = resultSet.getInt("OrderQTY");
                int Discount = resultSet.getInt("Discount");
                preparedStatement1.setObject(1,itemCode);
                ResultSet resultSet1 = preparedStatement1.executeQuery();

                while (resultSet1.next()){
                    itemWithDetails.add(new ItemWithDetails(
                            itemCode, OrderQTY, Discount,
                            resultSet1.getString("Description"),
                            resultSet1.getString("PackSize"),
                            resultSet1.getDouble("UnitPrice")
                    ));
                }
            }
            return itemWithDetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemDetails getItemDetails(String itemID) {
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
    @Override
    public Order getOrderDetails(String OrderID){
        Order order = null;
        String SQl = "SELECT OrderDate, CustID FROM orders WHERE OrderID = ? ;";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQl);
            preparedStatement.setObject(1,OrderID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                order = new Order(
                        OrderID,
                        resultSet.getDate("OrderDate").toLocalDate(),
                        resultSet.getString("CustID")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return order;
    }

    @Override
    public String getOrderID() {
        String SQL = "SELECT MAX(OrderID) FROM orders";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            String maxOrderId = resultSet.next() ? resultSet.getString(1) : "D000";

            int num = Integer.parseInt(maxOrderId.replace("D","")) +1;

            return  "D" + String.format("%03d",num);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<OrderWithDetails> getOrderbyID(String orderID) {
        String orderSQL = "SELECT OrderID, OrderDate, CustID FROM orders WHERE OrderID = ?";
        String detailSQL = "SELECT ItemCode, OrderQTY, Discount FROM orderdetail WHERE OrderID = ?";
        ObservableList<OrderWithDetails> orderWithDetails = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psOrder = connection.prepareStatement(orderSQL);
            PreparedStatement psDetail = connection.prepareStatement(detailSQL);

            psOrder.setObject(1,orderID);
            ResultSet resultSet = psOrder.executeQuery();

            if (resultSet.next()){
                LocalDate orderDate = resultSet.getDate("OrderDate").toLocalDate();
                String custID = resultSet.getString("CustID");

                psDetail.setString(1,orderID);
                ResultSet resultSet1 = psDetail.executeQuery();

                while (resultSet1.next()){
                    orderWithDetails.add(new OrderWithDetails(
                            orderID,orderDate,custID,
                            resultSet1.getString("ItemCode"),
                            resultSet1.getInt("OrderQTY"),
                            resultSet1.getInt("Discount")
                    ));
                }
            }
            return orderWithDetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
