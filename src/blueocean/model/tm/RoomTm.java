package blueocean.model.tm;

import javafx.scene.control.ProgressBar;

public class RoomTm {
    private String id;
    private String typecode;
    private String type;
    private String details;
    private String note;
    private String availability;
    private ProgressBar pbar;

    public RoomTm(String id, String typecode, String type, String details, String note, String availability, ProgressBar pbar) {
        this.id = id;
        this.typecode = typecode;
        this.type = type;
        this.details = details;
        this.note = note;
        this.availability = availability;
        this.pbar = pbar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public ProgressBar getPbar() {
        return pbar;
    }

    public void setPbar(ProgressBar pbar) {
        this.pbar = pbar;
    }
}
