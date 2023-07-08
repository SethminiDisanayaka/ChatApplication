package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController extends Thread {

    private AnchorPane ClientPane;
    private AnchorPane EmojiPane;
    public ScrollPane ScrollPane;
    private Label lblName;
    private TextField txtMessage;
    public javafx.scene.layout.VBox VBox;

    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    private FileChooser fileChooser;
    private File filePath;

    public ClientController() {
    }

    public void initialize(){
    String name = LoginController.username;
    lblName.setText(name);

        try{
            socket= new Socket("localhost",4500);
            System.out.println("Socket connected to server");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            this.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
            EmojiPane.setVisible(false);
    }

    @Override
    public void run(){
        try{

            while (true){

                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];

                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]+" ");
                }

                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }

                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }

                if (firstChars.equalsIgnoreCase("img")) {
                    //for the Images

                    st = st.substring(3, st.length() - 1);


                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);


                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(lblName.getText())) {

                        VBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1 = new Text("  " + cmd + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text("");
                        hBox.getChildren().add(text1);

                    }

                    Platform.runLater(() -> VBox.getChildren().addAll(hBox));


                } else {

                    TextFlow tempFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(lblName.getText() + ":")) {
                        Text txtName = new Text(cmd + " ");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);

                        tempFlow.setStyle("-fx-color: rgb(239,242,255);" +
                                "-fx-background-color: rgb(48,224,224);" +
                                " -fx-background-radius: 10px");
                        tempFlow.setPadding(new Insets(3,10,3,10));
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200); //200

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(12); //12




                    if (!cmd.equalsIgnoreCase(lblName.getText() + ":")) {


                        VBox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);

                    } else {

                        Text text2 = new Text(fullMsg + " ");
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow2);
                        hBox.setPadding(new Insets(2,5,2,10));

                        flow2.setStyle("-fx-color: rgb(239,242,255);" +
                                "-fx-background-color: rgb(9,241,148);" +
                                "-fx-background-radius: 10px");
                        flow2.setPadding(new Insets(3,10,3,10));
                    }

                    Platform.runLater(() -> VBox.getChildren().addAll(hBox));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Mouse_On_Click_Hide_Emogi(MouseEvent event) { EmojiPane.setVisible(false);}

    @FXML
    void btn_image_on_action(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(lblName.getText() + " " + "img" + filePath.getPath());
    }

    @FXML
    void btn_imogi_on_action(MouseEvent event) {
        EmojiPane.setVisible(true);
    }

    @FXML
    void btn_logout_On_action(MouseEvent event) { System.exit(0);}

    @FXML
    void btn_massage_send_on_action(MouseEvent event) {
        String msg = txtMessage.getText();
        writer.println(lblName.getText() + ": " + msg);

        txtMessage.clear();

        if(msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);

        }
    }

    @FXML
    void emoji1(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji10(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji11(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji12(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji2(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji3(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji4(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji5(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji6(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji7(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji8(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void emoji9(MouseEvent event) {
        String emoji = new String(Character.toChars(128546));
        txtMessage.setText(emoji);
        EmojiPane.setVisible(false);
    }

    @FXML
    void msgOnAction(ActionEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(lblName.getText() + " " + "img" + filePath.getPath());
    }

}
