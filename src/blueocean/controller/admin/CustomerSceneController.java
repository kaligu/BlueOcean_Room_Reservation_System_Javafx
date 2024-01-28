package blueocean.controller.admin;

import blueocean.model.Customer;
import blueocean.model.tm.CustomerTm;
import blueocean.ramdb.RamDatabase;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.util.Optional;

public class CustomerSceneController {

    public TableColumn<CustomerTm,String> colnamecustomer;
    public TableColumn<CustomerTm,String> colniccustomer;
    public TableColumn<CustomerTm,String> colphonecustomer;
    public TableColumn<CustomerTm,String> colmailcustomer;
    public TableColumn<CustomerTm,String> coladdresscustomer;
    public TableColumn<CustomerTm,String> coloptioncustomer;
    public TableView<CustomerTm> tablcustomer;

    public TextField txtfldname;
    public TextField txtfldnic;
    public TextField txtfldtell;
    public TextField txtfldaddress;
    public TextField txtfldemail;
    public JFXButton btnaddcustomer;
    public JFXButton btnupdatecustomer;

    public void initialize(){
        colnamecustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
        colniccustomer.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colphonecustomer.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colmailcustomer.setCellValueFactory(new PropertyValueFactory<>("email"));
        coladdresscustomer.setCellValueFactory(new PropertyValueFactory<>("address"));
        coloptioncustomer.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadData();
        selectRowAndUpdate();
    }

    private void selectRowAndUpdate() {
        tablcustomer.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                if(null!=newValue) {
                    setData(newValue);
                }
        }));
    }

    private void setData(CustomerTm ct) {
        txtfldname.setText(ct.getName());
        txtfldnic.setText(ct.getNic());
        txtfldtell.setText(ct.getTel());
        txtfldemail.setText(ct.getEmail());
        txtfldaddress.setText(ct.getAddress());
        btnupdatecustomer.setText("Update Customer");
    }

    private void loadData() {
        ObservableList<CustomerTm> obl = FXCollections.observableArrayList();
        for(Customer c: RamDatabase.customerdb){
            Button btn = new Button("Delete");
            CustomerTm ct = new CustomerTm(c.getName(),c.getNic(),c.getTel(),c.getEmail(),c.getAddress(),btn);
            obl.add(ct);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether do you want to delete this customer?", ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if(buttonType.get() == ButtonType.YES){
                    boolean isDeleted = RamDatabase.customerdb.remove(c);
                    if(isDeleted){
                        new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                        clearFields();
                        loadData();
                    }else{
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                        loadData();
                    }
                }
            });
        }
        tablcustomer.setItems(obl);
    }

    private void clearFields(){
        txtfldname.clear();
        txtfldnic.clear();
        txtfldtell.clear();
        txtfldemail.clear();
        txtfldaddress.clear();
    }

    public void onActionAdd(ActionEvent actionEvent) {
        clearFields();
        txtfldname.requestFocus();
        btnupdatecustomer.setText("Save Customer");
    }

    public void onActionUpdate(ActionEvent actionEvent) {
        Customer ctx = new Customer(
                txtfldname.getText(),
                txtfldnic.getText(),
                txtfldtell.getText(),
                txtfldemail.getText(),
                txtfldaddress.getText()
        );

        if(btnupdatecustomer.getText().equalsIgnoreCase("Save Customer")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether do you want to Save this customer?", ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.get() == ButtonType.YES){
                boolean isSaved = RamDatabase.customerdb.add(ctx);
                if(isSaved){
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
                    clearFields();
                    loadData();
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    loadData();
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether do you want to Update this customer?", ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            boolean isUpdated = false;
            if(buttonType.get() == ButtonType.YES){
                for(int i = 0; i< RamDatabase.customerdb.size(); i++){
                    if(txtfldnic.getText().equalsIgnoreCase(RamDatabase.customerdb.get(i).getNic())){
                        RamDatabase.customerdb.get(i).setNic(txtfldnic.getText());
                        RamDatabase.customerdb.get(i).setName(txtfldname.getText());
                        RamDatabase.customerdb.get(i).setTel(txtfldtell.getText());
                        RamDatabase.customerdb.get(i).setAddress(txtfldaddress.getText());
                        RamDatabase.customerdb.get(i).setEmail(txtfldemail.getText());
                        isUpdated = true;
                    }
                }
                if(isUpdated){
                    new Alert(Alert.AlertType.INFORMATION, "Customer Updated!").show();
                    loadData();
                    clearFields();
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    loadData();
                }
            }
        }
    }

    public void enterActionTxtFldName(ActionEvent actionEvent) {
        txtfldnic.requestFocus();
    }

    public void enterActionTxtFldNic(ActionEvent actionEvent) {
        txtfldtell.requestFocus();
    }

    public void enterActionTxtFldTell(ActionEvent actionEvent) {
        txtfldaddress.requestFocus();
    }

    public void enterActionTxtFldAddress(ActionEvent actionEvent) {
        txtfldemail.requestFocus();
    }

    public void enterActionTxtFldEmail(ActionEvent actionEvent) {
        btnupdatecustomer.requestFocus();
        btnupdatecustomer.fire();
    }

    public void searchonActionTxtfldName(KeyEvent keyEvent) {
        searchCustomerByName(txtfldname.getText());
    }

    private void searchCustomerByName(String txt) {
        tablcustomer.getItems().clear();
        ObservableList<CustomerTm> oblx = FXCollections.observableArrayList();
        for(Customer c: RamDatabase.customerdb){
            if(c.getName().toLowerCase().contains(txt.toString().toLowerCase())){ //check ignore case
                Button btn = new Button("Delete");
                CustomerTm ct = new CustomerTm(c.getName(), c.getNic(), c.getTel(), c.getEmail(), c.getAddress(), btn);
                oblx.add(ct);
            }
        }
        tablcustomer.setItems(oblx);
    }
}
