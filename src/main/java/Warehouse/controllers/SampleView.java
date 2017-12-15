package Warehouse.controllers;


import Sale.controllers.DataManager;
import Sale.models.Requisition;
import Sale.models.RequisitionGoods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class SampleView {

    private List<Requisition> requisitionList = new ArrayList<Requisition>();
    private List<RequisitionGoods> RequisitionGoodsList = new ArrayList<RequisitionGoods>();
    private ObservableList<RequisitionGoods> observableList;
    private  List<RequisitionGoods> requisitionGoodsList = new ArrayList<RequisitionGoods>();
    @FXML
    private TableView reqGoodsLstTableView;
    @FXML
    private TableColumn<RequisitionGoods,Integer> idCol, quanCol;
    @FXML
    private TableColumn<RequisitionGoods,String> typeCol, brandCol, nameCol;

    private DataManager dataManager;
    public SampleView() {
    }
    @FXML
    public void initialize(){

        this.idCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("id"));
        this.typeCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("type"));
        this.brandCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("brand"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("name"));
        this.quanCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("amount"));

        observableList = FXCollections.observableList(RequisitionGoodsList);
        reqGoodsLstTableView.setItems(observableList);

    }

    public void reTableReq(){
        ArrayList<Integer> num = new ArrayList<Integer>();

        requisitionGoodsList.clear();
        for (Requisition r : requisitionList) {
            for (RequisitionGoods req : r.getRequisitionGoodsArrayList()) {
                RequisitionGoods rg = new RequisitionGoods(req.getId(), req.getType(), req.getBrand(), req.getName(), req.getAmount());
                RequisitionGoodsList.add(rg);
            }
        }
        for (RequisitionGoods reqTing :RequisitionGoodsList) {

            if (!num.contains(reqTing.getId())){
                num.add(reqTing.getId());
                requisitionGoodsList.add(reqTing);
            }
            else{
                for(RequisitionGoods g : requisitionGoodsList){
                    if(g.getId() == reqTing.getId()){
                        g.setAmount(g.getAmount()+reqTing.getAmount());
                    }
                }
            }
        }

        for (Requisition rq: requisitionList) {
            dataManager.setRequisitionStatus(rq.getId(),"picking");
        }
        observableList = FXCollections.observableList(requisitionGoodsList);
        reqGoodsLstTableView.setItems(observableList);
    }


    public List<Requisition> getRequisitionList() {
        return requisitionList;
    }

    public void setRequisitionList(List<Requisition> requisitionList) {
        this.requisitionList = requisitionList;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
