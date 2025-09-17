package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    Stage coustomerManagement = new Stage();
    Stage itemManagement = new Stage();
    Stage OrderManagement = new Stage();

    @FXML
    void btnCustomerMgtOnAction(ActionEvent event)  {

        try {
            coustomerManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        coustomerManagement.setResizable(false);
        coustomerManagement.show();
    }

    @FXML
    void btnItemMgtOnAction(ActionEvent event) {

        try {
            itemManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ItemManagementForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        itemManagement.setResizable(false);
        itemManagement.show();
    }

    @FXML
    void btnOrderDetailMgtOnAction(ActionEvent event) {
        System.out.println("order detail management");
    }

    @FXML
    void btnOrderMgtOnAction(ActionEvent event) {
        try {
            OrderManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/OrderManagementForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OrderManagement.setResizable(false);
        OrderManagement.show();
    }
}
