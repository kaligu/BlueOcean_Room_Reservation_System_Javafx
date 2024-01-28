package blueocean.controller.admin;

import blueocean.model.Room;
import blueocean.model.tm.RoomTm;
import blueocean.ramdb.RamDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class RoomSceneController {

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
}
