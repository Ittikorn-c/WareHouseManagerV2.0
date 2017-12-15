package Sale.controllers;

import Sale.dataSources.DBConnector;
import Sale.models.Goods;
import Sale.models.Requisition;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataManager {
    private ArrayList<Goods> goodses = new ArrayList<Goods>();
    private ArrayList<Requisition> requisitions = new ArrayList<Requisition>();
    private ArrayList<Requisition> purchases = new ArrayList<Requisition>();
    private DBConnector dbConnector = new DBConnector();

    public DataManager(){
        getAllGoods();
        this.requisitions = getRequisitions();
    }

    public void setRequisitionStatus(int id, String status) {
        for (Requisition requisition : requisitions) {
            if (requisition.getId() == id)
                requisition.setStatus(status);
        }
        dbConnector.setRequisitionStatus(id, status);
    }

    public void getAllGoods(){
        goodses = dbConnector.getAllGoodses();
    }

    public void insertRequisition(Requisition requisition){
        dbConnector.insertRequisition(requisition);
        requisitions.add(requisition);
    }

    public ArrayList<Goods> getGoodses() {
        return goodses;
    }

    public void setGoodses(ArrayList<Goods> goodses) {
        this.goodses = goodses;
    }

    public ArrayList<Requisition> getRequisitions() {
        try {
            requisitions = dbConnector.getAllRequisitions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requisitions;
    }

    public void setRequisitions(ArrayList<Requisition> requisitions) {
        this.requisitions = requisitions;
    }

    public void updateGoods(Goods goods) {
        dbConnector.updateGoods(goods);
    }
}
