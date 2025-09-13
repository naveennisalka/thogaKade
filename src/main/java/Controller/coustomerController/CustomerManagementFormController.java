package Controller.coustomerController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CoustomerDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerManagementFormController implements Initializable {



    ObservableList <CoustomerDetails> coustomerDetails = FXCollections.observableArrayList();
    CustomerManagementService coustomerManagementService = new CustomerManagementController();

    @FXML
    private JFXTextField customerAddress;

    @FXML
    public JFXTextField txtcustomerID;

    @FXML
    private JFXTextField customerCity;

    @FXML
    private DatePicker customerDOB;

    @FXML
    private JFXTextField customerName;

    @FXML
    private JFXTextField customerPostalCode;

    @FXML
    private JFXComboBox<String> customerProvince;

    @FXML
    private JFXTextField customerSalary;

    @FXML
    private JFXComboBox<String> customerTitle;

    @FXML
    private TableView<CoustomerDetails> tblCoustomerDetalis;

    @FXML
    private TableColumn<?, ?> tblCustomerAddress;

    @FXML
    private TableColumn<?, ?> tblCustomerCity;

    @FXML
    private TableColumn<?, ?> tblCustomerDOB;

    @FXML
    private TableColumn<?, ?> tblCustomerName;

    @FXML
    private TableColumn<?, ?> tblCustomerProvince;

    @FXML
    private TableColumn<?, ?> tblCustomerPsotalCode;

    @FXML
    private TableColumn<?, ?> tblCustomerSalary;

    @FXML
    private TableColumn<?, ?> tblCustomerTitle;

    @FXML
    public TableColumn<?, ?> tblCustomerID;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        CoustomerDetails details = new CoustomerDetails(
                txtcustomerID.getText(),
                customerTitle.getValue(),
                customerName.getText(),
                customerDOB.getValue(),
                Double.parseDouble(customerSalary.getText()),
                customerAddress.getText(),
                customerCity.getText(),
                customerProvince.getValue(),
                Integer.parseInt(customerPostalCode.getText())
        );

        coustomerManagementService.addCoustomerDetails(details);
        loadCoustomerDetails();


    }

    @FXML
    void btnClearFormOnAction(ActionEvent event) {
        txtcustomerID.setText(null);
        customerAddress.setText(null);
        customerCity.setText(null);
        customerDOB.getEditor().clear();
        customerName.setText(null);
        customerPostalCode.setText(null);
        customerSalary.setText(null);
        //comboBox
        customerProvince.getSelectionModel().clearSelection();
        customerTitle.getSelectionModel().clearSelection();
    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {
        coustomerManagementService.deleteCoustomeDetails(txtcustomerID.getText());
        loadCoustomerDetails();
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {

        CoustomerDetails details = new CoustomerDetails(
                txtcustomerID.getText(),
                customerTitle.getValue(),
                customerName.getText(),
                customerDOB.getValue(),
                Double.parseDouble(customerSalary.getText()),
                customerAddress.getText(),
                customerCity.getText(),
                customerProvince.getValue(),
                Integer.parseInt(customerPostalCode.getText())
        );

        coustomerManagementService.updateCoustomerDetails(details);
        loadCoustomerDetails();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList <String> customerTitleTypes = FXCollections.observableArrayList(
            "Miss", "Mr"
        );

        ObservableList<String> ProvinceType = FXCollections.observableArrayList(
                "Central", "Eastern", "North Central", "Northern", "North Western", "Sabaragamuwa", "Southern", "Uva", "Western"
        );
        customerTitle.setItems(customerTitleTypes);
        customerProvince.setItems(ProvinceType);

        //set table details
        tblCustomerID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tblCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblCustomerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblCustomerDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        tblCustomerSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tblCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomerCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tblCustomerProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        tblCustomerPsotalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        loadCoustomerDetails();
    }

    private void loadCoustomerDetails(){
        coustomerDetails.clear();
        coustomerDetails = coustomerManagementService.getAllCoustomerDetails();
        tblCoustomerDetalis.setItems(coustomerDetails);
    }
}
