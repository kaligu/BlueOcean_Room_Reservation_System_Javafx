package blueocean.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginWindowController {
    public ImageView btnclse;
    public Button btnlgin;
    public Text btnclr;
    public ComboBox cmbrole;
    public TextField txtfldusrnme;
    public AnchorPane LoginWindowContext;
    public PasswordField txtfldpswrd;

    private String admin = "admin";
    private String pw = "1234";
    private String checkAdmin, checkPw;

    private String recept = "recept";
    private String rpw = "1234";
    private String checkRecept, checkrpw;

    public void initialize(){
        ObservableList<String> data = FXCollections.observableArrayList("Admin", "Receptionist"); //set values to array
        cmbrole.setItems(data); //add array values to combobox
    }

    public void mouseEnteredToClose(MouseEvent mouseEvent) {
        btnclse.setImage(new Image ("blueocean/images/icons8-close-48.png"));
    }

    public void mouseExitedToClose(MouseEvent mouseEvent) {
        btnclse.setImage(new Image ("blueocean/images/icons8-close-50.png"));
    }

    public void mouseClickedToClose(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void mouseEnteredToLogin(MouseEvent mouseEvent) {
        btnlgin.setStyle( "-fx-background-color: rgba(128, 135, 168, 1); -fx-font-size:25;");
    }

    public void mouseExitedToLogin(MouseEvent mouseEvent) {
        btnlgin.setStyle( "-fx-background-color:  rgba(88, 107, 144, 1); -fx-font-size:25;");
    }

    public void mouseEnteredToClear(MouseEvent mouseEvent) {
        btnclr.setStyle( "-fx-fill: RED; -fx-font-size:25; -fx-underline: true;");
    }

    public void mouseExitedToClear(MouseEvent mouseEvent) {
        btnclr.setStyle( "-fx-fill: BLACK; -fx-font-size:25; -fx-underline: true;");
    }

    public void OnActionCmbrole(ActionEvent actionEvent) {
        txtfldusrnme.setDisable(false);
        txtfldpswrd.setDisable(false);
    }

    public void cleartxtflds(){
        txtfldusrnme.clear();
        txtfldpswrd.clear();
    }

    public void OnActionbtnlgn(ActionEvent actionEvent) throws IOException {
        checkAdmin = txtfldusrnme.getText().toString();
        checkPw = txtfldpswrd.getText().toString();
        checkRecept = txtfldusrnme.getText().toString();
        checkrpw= txtfldpswrd.getText().toString();
        if(checkAdmin.equals(admin) && checkPw.equals(pw)){
            Stage stageloginwindow = (Stage)LoginWindowContext.getScene().getWindow();
            stageloginwindow.close();

            Stage stage= new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.centerOnScreen();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminMainWindow.fxml"))));
            stage.show();
        }else if(checkRecept.equals(recept) && checkrpw.equals(rpw)){
            Stage stageloginwindow = (Stage)LoginWindowContext.getScene().getWindow();
            stageloginwindow.close();

            Stage stage= new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.centerOnScreen();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptMainWindow.fxml"))));
            stage.show();
        }
        txtfldusrnme.setText("");
        txtfldpswrd.setText("");
    }

    public void onActionClearFields(MouseEvent mouseEvent) {
        cleartxtflds();
    }
}
