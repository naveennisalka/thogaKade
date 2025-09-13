package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    Stage coustomerManagement = new Stage();

    @FXML
    void btnCustomerMgtOnAction(ActionEvent event)  {
        System.out.println("Coustomer management");
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
        System.out.println("Item management");

    }

    @FXML
    void btnOrderDetailMgtOnAction(ActionEvent event) {
        System.out.println("order detail management");
    }

    @FXML
    void btnOrderMgtOnAction(ActionEvent event) {
        System.out.println("order management");

    }

}
