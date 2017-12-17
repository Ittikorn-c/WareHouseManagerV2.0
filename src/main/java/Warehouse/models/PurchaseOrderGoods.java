package Warehouse.models;

import Sale.models.Goods;

public class PurchaseOrderGoods extends Goods{
    protected int id;
    protected String type;
    protected String brand;
    protected String name;
    protected int amount;
//    protected int available;

    public PurchaseOrderGoods(int id, String type, String brand, String name, int amount) {
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
