package Sale.models;

import java.util.ArrayList;

public class Requisition {
    private int id;
    private String status;
    private ArrayList<RequisitionGoods> requisitionGoodsArrayList;

    public Requisition(int id, String status, ArrayList<RequisitionGoods> requisitionGoodsArrayList) {
        this.id = id;
        this.status = status;
        this.requisitionGoodsArrayList = requisitionGoodsArrayList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<RequisitionGoods> getRequisitionGoodsArrayList() {
        return requisitionGoodsArrayList;
    }

    public void setRequisitionGoodsArrayList(ArrayList<RequisitionGoods> requisitionGoodsArrayList) {
        this.requisitionGoodsArrayList = requisitionGoodsArrayList;
    }
}
