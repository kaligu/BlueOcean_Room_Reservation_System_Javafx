package blueocean.controller;

import blueocean.model.Room;
import blueocean.ramdb.RamDatabase;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdminMainWindowController {
    public Button btnbktolgin;
    public AnchorPane ankrpanmainadminwindow;
    public HBox vboxrooms;
    public HBox vboxrooms1;
    public HBox vboxrooms3;
    public HBox vboxrooms4;
    public HBox vboxrooms5;
    public HBox vboxrooms6;
    public HBox vboxrooms7;

    public AnchorPane mainankrpan;
    public Button btnbckhome;
    public AnchorPane ankrpanefirst;
    public ImageView firstimg1;
    public ImageView firstimg2;
    public ImageView firstimg3;
    public ImageView firstimg4;
    public ImageView firstimg5;
    public Text firstimg6;
    public Text firstimg7;
    public ImageView firstimgcrtbkg;
    public Text firstlblbooked;
    public Text firstlblnotbooked;
    public Button btnexit;
    public Button btncstmr;
    public Button btnrooms;
    public Label lbltimeupdate;

    public JFXButton btnrefresh;
    public Text firstimg8;
    public Text firstimg61;
    public Button btnincomereports;
    public Button btnmanagerooms;
    public Button btnmanagemeals;

    private String clickedcolourblue;
    private String notclickedcolourblue;


    ArrayList<Button> buttons = new ArrayList<Button>();
    private int l;


    public void initialize() {
        setTime();//load datetim
        l=0;
        loadRoomArchi();
        clickedcolourblue=("-fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#395cab, #223768),\n" +
                "            linear-gradient(#728cbe, #263e75),\n" +
                "            linear-gradient(#7ebcea, #6a7998);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 17px;\n" +
                "    -fx-font-weight:bold;");
        notclickedcolourblue=("-fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#7ebcea, #2f4b8f),\n" +
                "            linear-gradient(#426ab7, #263e75),\n" +
                "            linear-gradient(#395cab, #223768);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 15.5px;");
    }

    private void loadRoomArchi(){
        int countavailable =0;
        for(Room r:RamDatabase.roomdb){
            setRoomsInGui(r.getId() , r.getTypecode(), r.getNote(), r.getAvailability());
            if(r.getAvailability().equals("true")){
                countavailable++;
            }
        }
        firstlblnotbooked.setText(String.valueOf(new Integer(countavailable)));
        firstlblbooked.setText(String.valueOf(new Integer(RamDatabase.roomdb.size()-countavailable)));
    }

    private void setTime() {
        lbltimeupdate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    public void setRoomsInGui(String id , String type, String note, String bool ) {
        if(bool.equals("false")){
            buttons.add(new Button());
            (buttons.get(l)).setText(id+"\n"+type+"\n"+note);
            buttons.get(l).setStyle( "-fx-background-color:red;  -fx-font-size:13; -fx-text-fill:white; -fx-border-color:black; -fx-min-height:100; -fx-min-width:59;-fx-max-height:100; -fx-max-width:59; -fx-border-width:2;");
            buttons.get(l).setDisable(true);
            addbutton();
        }else if(bool.equals("maintain")){
            buttons.add(new Button());
            (buttons.get(l)).setText(id+"\n"+type+"\n"+note);
            buttons.get(l).setStyle( "-fx-background-color:orange;  -fx-font-size:13; -fx-text-fill:white; -fx-border-color:black; -fx-min-height:100; -fx-min-width:59;-fx-max-height:100; -fx-max-width:59; -fx-border-width:2;");
            buttons.get(l).setDisable(true);
            addbutton();
        }else{
            buttons.add(new Button());
            (buttons.get(l)).setText(id+"\n"+type+"\n"+note);
            buttons.get(l).setStyle( "-fx-background-color:  green; -fx-font-size:13; -fx-text-fill:white; -fx-border-color:black; -fx-min-height:100; -fx-min-width:59;-fx-max-height:100; -fx-max-width:59; -fx-border-width:2;");
            addbutton();
        }
    }

    public void addbutton(){
        if(buttons.size()<6&&buttons.size()>=0) {
            vboxrooms.getChildren().add(buttons.get(l));
        }else if(buttons.size()>=6&&buttons.size()<11){
            vboxrooms1.getChildren().add(buttons.get(l));
        }else if(buttons.size()>=11&&buttons.size()<16){
            vboxrooms3.getChildren().add(buttons.get(l));
        }else if(buttons.size()>=16&&buttons.size()<21){
            vboxrooms4.getChildren().add(buttons.get(l));
        }else if(buttons.size()>=21&&buttons.size()<26){
            vboxrooms5.getChildren().add(buttons.get(l));
        }else if(buttons.size()>=26&&buttons.size()<31){
            vboxrooms6.getChildren().add(buttons.get(l));
        }else if(buttons.size()>=31&&buttons.size()<36){
            vboxrooms7.getChildren().add(buttons.get(l));
        }
        l++;
    }

    public void onActionckToLogin(ActionEvent actionEvent) throws IOException {
        setResetButtonColourBlue();
        openLoginWindow();
    }

    private void setScene(String ui) throws IOException {
        mainankrpan.setStyle("-fx-background-color:black; -fx-opacity:0.83");
        Parent parent = FXMLLoader.load(getClass().getResource("../view/scenes/"+ui+".fxml"));
        mainankrpan.getChildren().clear();
        mainankrpan.getChildren().add(parent);
        clearAllComponentsMainAnkrPan(false);
        firstimgcrtbkg.setVisible(true);
    }

    private void openLoginWindow() throws IOException {
        Stage primarystage = (Stage) mainankrpan.getScene().getWindow();
        primarystage.close(); //stop admin main window stage
        Stage stage= new Stage(); //create new stage to show login scene
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginWindow.fxml"))));
        stage.show();
    }

    private void clearAllComponentsMainAnkrPan(boolean is){
        firstimg1.setVisible(is);
        firstimg2.setVisible(is);
        firstimg3.setVisible(is);
        firstimg4.setVisible(is);
        firstimg5.setVisible(is);
        firstimg6.setVisible(is);
        firstimg7.setVisible(is);
        firstimg8.setVisible(is);
        firstimgcrtbkg.setVisible(is);
        firstlblbooked.setVisible(is);
        firstimg61.setVisible(is);
        firstlblnotbooked.setVisible(is);
    }
    private void setResetButtonColourBlue(){
        btncstmr.setStyle(notclickedcolourblue);
        btnrooms.setStyle(notclickedcolourblue);
        btnincomereports.setStyle(notclickedcolourblue);
        btnmanagemeals.setStyle(notclickedcolourblue);
    }
    public void onActionExit(ActionEvent actionEvent) {
        Platform.exit(); //close the programme
    }

    public void FirstScene() throws IOException {
        mainankrpan.getChildren().clear();
        mainankrpan.setStyle("-fx-background-color:black; -fx-opacity:0.36");
        clearAllComponentsMainAnkrPan(true);
        mainankrpan.getChildren().add(firstimg1);
        mainankrpan.getChildren().add(firstimg2);
        mainankrpan.getChildren().add(firstimg3);
        mainankrpan.getChildren().add(firstimg4);
        mainankrpan.getChildren().add(firstimg5);
        mainankrpan.getChildren().add(firstimg6);
        mainankrpan.getChildren().add(firstimg7);
        mainankrpan.getChildren().add(firstimg8);
        mainankrpan.getChildren().add(firstimg61);
        mainankrpan.getChildren().add(firstimgcrtbkg);
        mainankrpan.getChildren().add(firstlblbooked);
        mainankrpan.getChildren().add(firstlblnotbooked);
    }

    public void onActionBackHome(ActionEvent actionEvent) throws IOException {
        setResetButtonColourBlue();
        FirstScene();
    }

    public void OnActionbtnCustomers(ActionEvent actionEvent) throws IOException {
        setResetButtonColourBlue();
        btncstmr.setStyle(clickedcolourblue);
        setScene("CustomersScene");
    }

    public void OnActionbtnrooms(ActionEvent actionEvent) throws IOException {
        setResetButtonColourBlue();
        btnrooms.setStyle(clickedcolourblue);
        setScene("RoomsScene");
    }

    public void onMouseEnterFirstimgcrtbkg(MouseEvent mouseEvent) {
        firstimgcrtbkg.setImage(new Image("blueocean/images/icons8appointment-64.png"));
    }

    public void onMouseExitedFirstimgcrtbkg(MouseEvent mouseEvent) {
        firstimgcrtbkg.setImage(new Image("blueocean/images/plus.png"));
    }

    public void onActionRefresh(ActionEvent actionEvent) throws IOException {
        Stage primarystage = (Stage) mainankrpan.getScene().getWindow();
        primarystage.close(); //stop admin main window stage
        Stage stage= new Stage(); //create new stage to show login scene
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminMainWindow.fxml"))));
        stage.show();
    }

    public void onActionmanageMeals(ActionEvent actionEvent) {
        setResetButtonColourBlue();
      //  btncnslbckg.setStyle(clickedcolourblue);
      //  setScene("CancelBookngScene");
    }

    public void OnActionbtnincomereports(ActionEvent actionEvent) throws IOException {
        setResetButtonColourBlue();
        btnincomereports.setStyle(clickedcolourblue);
        setScene("IncomeReportsScene");
    }

    public void onActionFirstimgcrtbkg(MouseEvent mouseEvent) {
    }

    public void OnActionbtnroomsmanage(ActionEvent actionEvent) throws IOException {
        setResetButtonColourBlue();
        btnrooms.setStyle(clickedcolourblue);
        setScene("ManageRoomsScenes");
    }
}
