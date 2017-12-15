package Sale.models;

public class Goods {

    protected int id;
    protected String type;
    protected String brand;
    protected String name;
    protected int quantity;
//    protected int available;

    public Goods(int id, String type, String brand, String name, int quantity) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
