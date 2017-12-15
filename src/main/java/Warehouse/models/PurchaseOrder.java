package Warehouse.models;

import Sale.models.RequisitionGoods;

import java.util.ArrayList;

public class PurchaseOrder {
    private int id;
    private Supplier supplier;
    private String status;
    private ArrayList<PurchaseOrderGoods> requisitionGoodsArrayList;

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
