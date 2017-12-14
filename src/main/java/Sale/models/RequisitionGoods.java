package Sale.models;

public class RequisitionGoods {

    private int id;
    private String type;
    private String brand;
    private String name;
    private int amount;

    public RequisitionGoods(int id, String type, String brand, String name, int amount) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
