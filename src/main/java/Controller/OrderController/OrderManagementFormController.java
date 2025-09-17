package Controller.OrderController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.ItemDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderManagementFormController implements Initializable {

    OrderManagementService orderManagementService = new OrderManagementController();

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
    private TableView<?> tblOrders;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtItemDiscription;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXTextField txtQTY;

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
        txtOrderID.setText(null);
        txtQTY.setText(null);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> custID = FXCollections.observableArrayList(orderManagementService.getAllCoustomerID());
        ObservableList<String> ItemID = FXCollections.observableArrayList(orderManagementService.getAllItemID());
        dropDownCusID.setItems(custID);
        dropDownItemID.setItems(ItemID);


        dropDownCusID.getSelectionModel().selectedItemProperty().addListener((observableValue, details, newValue) -> {
            if(newValue != null){
                customerName.setText(orderManagementService.getCoustomerName(dropDownCusID.getValue()));
            }
        });

        dropDownItemID.getSelectionModel().selectedItemProperty().addListener((observableValue, details, newValue) -> {
            if(newValue != null){
                ItemDetails itemD = orderManagementService.getItemDetails(dropDownItemID.getValue());
                txtItemDiscription.setText(itemD.getDescription());
            }
        });

    }
}
