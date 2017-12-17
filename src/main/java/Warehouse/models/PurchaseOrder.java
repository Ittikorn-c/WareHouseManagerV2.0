package Warehouse.models;

import Sale.models.RequisitionGoods;

import java.util.ArrayList;

public class PurchaseOrder {
    private int id;
    private Supplier supplier;
    private String status;
    private ArrayList<PurchaseOrderGoods> requisitionGoodsArrayList;


    public PurchaseOrder(int id, String status, Supplier supplier, ArrayList<PurchaseOrderGoods> requisitionGoodsArrayList) {
        this.id = id;
        this.status = status;
        this.supplier = supplier;
        this.requisitionGoodsArrayList = requisitionGoodsArrayList;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<PurchaseOrderGoods> getRequisitionGoodsArrayList() {
        return requisitionGoodsArrayList;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
