package Sale.controllers;

import Sale.dataSources.DBConnector;
import Sale.models.Goods;
import Sale.models.Requisition;
import Warehouse.models.PurchaseOrder;
import Warehouse.models.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataManager {
    private ArrayList<Goods> goodses = new ArrayList<Goods>();
    public void loadAllGoodses(){
        goodses = dbConnector.loadAllGoodses();
    }
    public ArrayList<Goods> getGoodses() {
        return goodses;
    }
    public void setGoodses(ArrayList<Goods> goodses) {
        this.goodses = goodses;
    }

    private ArrayList<Requisition> requisitions = new ArrayList<Requisition>();
    private ArrayList<PurchaseOrder> purchases = new ArrayList<PurchaseOrder>();
    private DBConnector dbConnector = new DBConnector();

    private ArrayList<Supplier> suppliers = new ArrayList<>();
    public void loadAllSuppliers() {
        suppliers = dbConnector.loadAllSuppliers();
    }
    public Supplier getSupplier(String name) {
        for (Supplier supplier : suppliers) {
            if (supplier.getName().equals(name))
                return supplier;
        }
        return null;
    }
    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }
    public void setSuppliers(ArrayList<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public DataManager(){
        loadAllGoodses();
        loadAllSuppliers();
        this.requisitions = getRequisitions();
    }

    public void setRequisitionStatus(int id, String status) {
        for (Requisition requisition : requisitions) {
            if (requisition.getId() == id)
                requisition.setStatus(status);
        }
        dbConnector.setRequisitionStatus(id, status);
    }



    public void insertRequisition(Requisition requisition){
        dbConnector.insertRequisition(requisition);
        requisitions.add(requisition);
    }

    public void insertGoods(Goods goods) {
        dbConnector.insertGoodes(goods);
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



    public ArrayList<PurchaseOrder> getAllPurchases() {
        try {
            purchases = dbConnector.getAllPurchases();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchases;
    }

    public void setPurchaseOrderStatus(int id, String status) {
        for (PurchaseOrder po : purchases) {
            if(po.getId() == id){
                po.setStatus(status);
            }
        }
        dbConnector.setPurchaseOrderStatus(id, status);
    }

    public void updateGoodsAmount(int id, int amount) {
        dbConnector.updateGoodsAmount(id,amount);
    }

    public void insertPurchaseOrder(PurchaseOrder purchaseOrder) {
        dbConnector.insertPurchaseOrder(purchaseOrder);
    }

    ArrayList<String> allTypes;
    ArrayList<String> allBrands;
    ArrayList<String> supplierNames;

    public ArrayList<String> getAllTypes() {
        allTypes = getAllTypes("%");
        return allTypes;
    }

    public ArrayList<String> getAllTypes(String brand) {
        allTypes = dbConnector.getAllTypes(brand);
        return allTypes;
    }

    public ArrayList<String> getAllBrands() {
        allBrands = getAllBrands("%");
        return allBrands;
    }

    public ArrayList<String> getAllBrands(String type) {
        allBrands = dbConnector.getAllBrands(type);
        return allBrands;
    }

    public ArrayList<String> getAllSupNames() {
        supplierNames = dbConnector.getAllSupNames();
        return supplierNames;
    }

    public void updateStatusRequisition(Requisition r) {
        dbConnector.updateStatusRequisition(r);
    }

    public void addNewSupplier(Supplier supplier) {
        dbConnector.addNewSupplier(supplier);
        suppliers.add(supplier);
    }
}
