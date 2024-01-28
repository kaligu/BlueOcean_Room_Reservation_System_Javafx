package blueocean.model;

public class Meal {
    private String name;
    private String mealid;
    private String price;
    private String details;
    private String size;

    public Meal(String name, String mealid, String price, String details, String size) {
        this.name = name;
        this.mealid = mealid;
        this.price = price;
        this.details = details;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealid() {
        return mealid;
    }

    public void setMealid(String mealid) {
        this.mealid = mealid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
