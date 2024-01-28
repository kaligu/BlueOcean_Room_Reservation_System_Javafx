package blueocean.model.tm;

import javafx.scene.control.Button;

public class CustomerTm {
    private String name;
    private String nic;
    private String tel;
    private String email;
    private String address;
    private Button btn;

    public CustomerTm(String name, String nic, String tel, String email, String address, Button btn) {
        this.name = name;
        this.nic = nic;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.btn = btn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
