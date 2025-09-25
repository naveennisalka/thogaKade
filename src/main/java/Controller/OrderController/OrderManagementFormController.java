package Controller.OrderController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ItemDetails;
import model.ItemWithDetails;
import model.OrderDetails;
import model.OrderWithDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderManagementFormController implements Initializable {


    OrderManagementService orderManagementService = new OrderManagementController();
    ObservableList<OrderDetails> orderDetails = FXCollections.observableArrayList();
    ObservableList<OrderWithDetails> orderWithDetails = FXCollections.observableArrayList();
    ObservableList<ItemWithDetails> itemWithDetails = FXCollections.observableArrayList();;

    @FXML
    private TableColumn<?, ?> colDiscription;

    @FXML
    private TableColumn<?, ?> colPkgSize;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductID;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colUnitPrize;

    @FXML
    private JFXTextField customerName;

    @FXML
    private JFXComboBox<String> dropDownCusID;

    @FXML
    private JFXComboBox<String> dropDownItemID;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<ItemWithDetails> tblOrders;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtItemDiscription;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXTextField txtQTY;

    @FXML
    public DatePicker datepicker;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearFormOnAction(ActionEvent event) {
        customerName.setText(null);
        dropDownCusID.setValue(null);
        dropDownItemID.setValue(null);
        lblTotal.setText(null);
        txtDiscount.setText(null);
        txtItemDiscription.setText(null);
        txtOrderID.setText(orderManagementService.getOrderID());
        txtQTY.setText(null);
        datepicker.setValue(null);
    }

    @FXML
    void btnViewHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void btndeleteItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnplaceOrderOnAction(ActionEvent event) {

    }

    private void loadItemDetails(String orderID){
        itemWithDetails.clear();
        itemWithDetails = orderManagementService.getOrderedItemDetails(orderID);
        tblOrders.setItems(itemWithDetails);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> custID = FXCollections.observableArrayList(orderManagementService.getAllCoustomerID());
        ObservableList<String> ItemID = FXCollections.observableArrayList(orderManagementService.getAllItemID());
        dropDownCusID.setItems(custID);
        dropDownItemID.setItems(ItemID);


        txtOrderID.setText(orderManagementService.getOrderID());

//
//        colItemID.setCellValueFactory(new PropertyValueFactory<>("ID"));
//        colItemDiscription.setCellValueFactory(new PropertyValueFactory<>("Description"));
//        colSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
//        colPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
//        colQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
//        loadItemDetails();
//
//        private String orderID;
//        private LocalDate orderDate;
//        private String cutID;
//        private String itemCode;
//        private int orderQTY;
//        private int discount;

        colProductID.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDiscription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("OrderQTY"));
        colPkgSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colUnitPrize.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Discount"));

        loadItemDetails(txtOrderID.getText());


//


        dropDownCusID.getSelectionModel().selectedItemProperty().addListener((observableValue, details, newValue) -> {
            if(newValue != null){
                customerName.setText(orderManagementService.getCoustomerName(dropDownCusID.getValue()));
            }
        });

        txtOrderID.textProperty().addListener((observable, oldValue, newValue) -> {
//            colProductID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
//            colDiscription.setCellValueFactory(new PropertyValueFactory<>(""));
              loadItemDetails(txtOrderID.getText());
            if(orderManagementService.getOrderDetails(txtOrderID.getText()) != null){
                dropDownCusID.setValue(orderManagementService.getOrderDetails(txtOrderID.getText()).getCustomerID());
                datepicker.setValue(orderManagementService.getOrderDetails(txtOrderID.getText()).getOrderDate());
            }

        });

        dropDownItemID.getSelectionModel().selectedItemProperty().addListener((observableValue, details, newValue) -> {
            if(newValue != null){
                ItemDetails itemD = orderManagementService.getItemDetails(dropDownItemID.getValue());
                txtItemDiscription.setText(itemD.getDescription());
            }
        });

        tblOrders.getSelectionModel().selectedItemProperty().addListener((observebleValue, oldvalue, newValue) -> {
            if(newValue != null){
                txtDiscount.setText(String.valueOf((newValue).getDiscount()));
                txtQTY.setText(String.valueOf((newValue).getOrderQTY()));
                dropDownItemID.setValue(newValue.getItemCode());
            }
        });





    }
}
