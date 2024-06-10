package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtname;
    static String username;

    @FXML
    private ImageView imgView;

    @FXML
    void NameOnAction(ActionEvent event) throws IOException {
        username = txtname.getText();
        txtname.clear();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(LoginController.class.getResource("/view/ClientForm.fxml"))));
        stage.close();
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/img/images.png"));
       /* Image image = new Image(getClass().getResourceAsStream("/img/pexels-olly-3760067.png"));*/
        imgView.setImage(image);
    }

}
