package Sale.models;

public class RequisitionGoods extends Goods {

    private int amount;

    public RequisitionGoods(int id, String type, String brand, String name, int amount) {
        super(id, type, brand, name, amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
