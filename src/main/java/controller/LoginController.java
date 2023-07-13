package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtname;
    static String username;

    @FXML
    void NameOnAction(ActionEvent event) throws IOException {
        username=txtname.getText();
        txtname.clear();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(LoginController.class.getResource("/view/ClientForm.fxml"))));
        stage.close();
        stage.centerOnScreen();
        stage.show();
    }

}
