package Controller.ItemController;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ItemDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemManagementFormController implements Initializable {

    ObservableList<ItemDetails> itemDetails = FXCollections.observableArrayList();
    ItemManagementService itemManagementService = new ItemManagementController();

    @FXML
    private TableColumn<?, ?> colItemDiscription;

    @FXML
    private TableColumn<?, ?> colItemID;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableView<ItemDetails> tblItemDetails;

    @FXML
    private JFXTextField txtItemDiscription;

    @FXML
    private JFXTextField txtItemID;

    @FXML
    private JFXTextField txtItemPrice;

    @FXML
    private JFXTextField txtItemSize;

    @FXML
    private JFXTextField txtItemUnits;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        ItemDetails details = new ItemDetails(
                txtItemID.getText(),
                txtItemDiscription.getText(),
                txtItemSize.getText(),
                Double.parseDouble(txtItemPrice.getText()),
                Integer.parseInt(txtItemUnits.getText())
        );

        itemManagementService.addItemDetails(details);
        loadItemDetails();
    }

    @FXML
    void btnClearFormOnAction(ActionEvent event) {
        txtItemDiscription.setText(null);
        txtItemID.setText(null);
        txtItemPrice.setText(null);
        txtItemSize.setText(null);
        txtItemUnits.setText(null);

    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        itemManagementService.deleteItemDetails(txtItemID.getText());
        loadItemDetails();
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        ItemDetails details = new ItemDetails(
                txtItemID.getText(),
                txtItemDiscription.getText(),
                txtItemSize.getText(),
                Double.parseDouble(txtItemPrice.getText()),
                Integer.parseInt(txtItemUnits.getText())
        );

        itemManagementService.updateItemDetails(details);
        loadItemDetails();

    }

    private void loadItemDetails(){
        itemDetails.clear();
        itemDetails = itemManagementService.getAllItemDetails();
        tblItemDetails.setItems(itemDetails);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        set table details
        colItemID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colItemDiscription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        loadItemDetails();

        tblItemDetails.getSelectionModel().selectedItemProperty().addListener((observableValue, details, newValue) -> {
            if(newValue != null){
                txtItemDiscription.setText(newValue.getDescription());
                txtItemID.setText(newValue.getID());
                txtItemUnits.setText(String.valueOf(newValue.getQTY()));
                txtItemPrice.setText(String.valueOf(newValue.getUnitPrice()));
                txtItemSize.setText(newValue.getPackSize());
            }
        });
    }
}
