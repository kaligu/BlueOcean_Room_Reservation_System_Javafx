package blueocean.controller.admin;

import blueocean.model.Room;
import blueocean.model.tm.CustomerTm;
import blueocean.model.tm.RoomTm;
import blueocean.ramdb.RamDatabase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Optional;

public class ManageRoomController {

    public TableView<RoomTm> tableroom;
    public TableColumn<RoomTm,String> colid;
    public TableColumn<RoomTm,String> coltypecode;
    public TableColumn<RoomTm,String> coltype;
    public TableColumn<RoomTm,String> coldetails;
    public TableColumn<RoomTm,String> colnote;
    public TableColumn<RoomTm,String> colavailability;
    public TableColumn<RoomTm,String> colpbar;
    public RadioButton rdbtnmaintain;
    public Text txt;
    public Text txt1;
    public JFXButton btnsaveroom;
    public JFXTextField txtid;
    public JFXTextField txttypecode;
    public JFXTextField txtdetails;
    public JFXTextField txtnote;
    public JFXTextField txttype;
    public JFXTextField txtavailability;
    public JFXButton btnaddroom;
    public JFXButton btndltroom;

    public void initialize(){
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coltypecode.setCellValueFactory(new PropertyValueFactory<>("typecode"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        coldetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        colnote.setCellValueFactory(new PropertyValueFactory<>("note"));
        colavailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colpbar.setCellValueFactory(new PropertyValueFactory<>("pbar"));
        loadData();
        selectRowAndChangeAvailbility();
    }

    private void selectRowAndChangeAvailbility() {
        tableroom.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(null!=newValue) {
                setData(newValue);
            }
        }));
    }

    private void setData(RoomTm rt) {
        txt1.setText(rt.getAvailability());
        txt.setText(rt.getId());

        txtid.setText(rt.getId());
        txttype.setText(rt.getType());
        txttypecode.setText(rt.getTypecode());
        txtavailability.setText(rt.getAvailability());
        txtdetails.setText(rt.getDetails());
        txtnote.setText(rt.getNote());
        btnsaveroom.setText("+Update Room");
    }

    private void loadData() {
        ObservableList<RoomTm> obl = FXCollections.observableArrayList();
        for(Room r: RamDatabase.roomdb){
            ProgressBar pbar = new ProgressBar();
            if(r.getAvailability().equalsIgnoreCase("false")){
                pbar.setProgress(1);
                pbar.setStyle(" -fx-accent: red;");
            }else if (r.getAvailability().equalsIgnoreCase("maintain")){
                pbar.setProgress(1);
                pbar.setStyle(" -fx-accent: orange;");
            }
            RoomTm rt = new RoomTm(r.getId(),r.getTypecode(),r.getType(),r.getDetails(),r.getNote(),r.getAvailability(),pbar);
            obl.add(rt);
        }
        tableroom.setItems(obl);
    }

    public void onActionbtnrdioroom(ActionEvent actionEvent) {
        String check= "";
        if(rdbtnmaintain.isSelected()){
            check="true";
        }else{
            check="false";
        }
        for (Room r : RamDatabase.roomdb){
            if(r.getId().equals(txt.getText())){
                if(check.equalsIgnoreCase("true")){
                    r.setAvailability("maintain");
                    loadData();
                }else{
                    r.setAvailability("true");
                    loadData();
                }
            }
        }
    }

    public void onActionTable(MouseEvent mouseEvent) {
        rdbtnmaintain.setDisable(false);
        for (Room r : RamDatabase.roomdb){
            if(r.getId().equals(txt.getText())){
                if(txt1.getText().equalsIgnoreCase("true")){
                    rdbtnmaintain.setSelected(false);
                }if(txt1.getText().equalsIgnoreCase("maintain")){
                    rdbtnmaintain.setSelected(true);
                }if(txt1.getText().equalsIgnoreCase("false")){
                    rdbtnmaintain.setDisable(true);
                }
            }
        }

    }

    public void cleartxt(){
        txtnote.clear();
        txtdetails.clear();
        txtavailability.clear();
        txttypecode.clear();
        txtid.clear();
        txtdetails.clear();
    }


    public void onActionaddRoom(ActionEvent actionEvent) {
        cleartxt();
        btnsaveroom.setText("Save Room");
    }

    public void onActionSave(ActionEvent actionEvent) {
        if(btnsaveroom.getText().equalsIgnoreCase("Save Room")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether do you want to Save this room?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            boolean isSaved = false;
            if (buttonType.get() == ButtonType.YES) {
                isSaved = RamDatabase.roomdb.add(new Room(txtid.getText(), txttypecode.getText(), txttype.getText(), txtdetails.getText(), txtnote.getText(), txtavailability.getText()));
                cleartxt();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Room Saved!").show();
                loadData();
                cleartxt();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                loadData();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether do you want to Update this Room?", ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            boolean isUpdated = false;
            if(buttonType.get() == ButtonType.YES){
                for(int i = 0; i< RamDatabase.roomdb.size(); i++){
                    if(txtid.getText().equalsIgnoreCase(RamDatabase.roomdb.get(i).getId())){
                        RamDatabase.roomdb.get(i).setId(txtid.getText());
                        RamDatabase.roomdb.get(i).setDetails(txtdetails.getText());
                        RamDatabase.roomdb.get(i).setTypecode(txttypecode.getText());
                        RamDatabase.roomdb.get(i).setType(txttype.getText());
                        RamDatabase.roomdb.get(i).setAvailability(txtavailability.getText());
                        RamDatabase.roomdb.get(i).setNote(txtnote.getText());
                        isUpdated = true;
                    }
                }
                if(isUpdated){
                    new Alert(Alert.AlertType.INFORMATION, "Room Updated!").show();
                    loadData();
                    cleartxt();
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    loadData();
                }
            }
        }
    }

    public void onActiondelete(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether do you want to Delete this Room?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        boolean isUpdated = false;
        if(buttonType.get() == ButtonType.YES){
            for(int i = 0; i< RamDatabase.roomdb.size(); i++){
                if(txtid.getText().equalsIgnoreCase(RamDatabase.roomdb.get(i).getId())){
                    RamDatabase.roomdb.remove(RamDatabase.roomdb.get(i));
                    isUpdated = true;
                }
            }
            if(isUpdated){
                new Alert(Alert.AlertType.INFORMATION, "Room Deleted!").show();
                loadData();
                cleartxt();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                loadData();
            }
        }
    }
}
