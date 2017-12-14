package Warehouse.controllers;


import Sale.models.Requisition;
import Sale.models.RequisitionGoods;
import Warehouse.models.RequisitingGoods;
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
    private List<RequisitingGoods> requisitingGoodsList = new ArrayList<RequisitingGoods>();
    private ObservableList<RequisitingGoods> observableList;
    @FXML
    private TableView reqGoodsLstTableView;
    @FXML
    private TableColumn<RequisitingGoods,Integer> idCol, quanCol;
    @FXML
    private TableColumn<RequisitingGoods,String> typeCol, brandCol, nameCol;


    public SampleView() {
    }
    @FXML
    public void initialize(){
        for (Requisition r : requisitionList) {
            for (RequisitionGoods req: r.getRequisitionGoodsArrayList()) {
                for (RequisitingGoods reqTing :requisitingGoodsList) {
                    if(req.getId() == reqTing.getId()){
                        reqTing.setAmount(req.getAmount()+ reqTing.getAmount());
                        break;
                    }
                    requisitingGoodsList.add(new RequisitingGoods(req.getId(), req.getType(), req.getBrand(),req.getName(),req.getAmount()));

                }


            }
        }
        this.idCol.setCellValueFactory(new PropertyValueFactory<RequisitingGoods, Integer>("id"));
        this.typeCol.setCellValueFactory(new PropertyValueFactory<RequisitingGoods, String>("type"));
        this.brandCol.setCellValueFactory(new PropertyValueFactory<RequisitingGoods, String>("brand"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory<RequisitingGoods, String>("name"));
        this.quanCol.setCellValueFactory(new PropertyValueFactory<RequisitingGoods, Integer>("amount"));

        observableList = FXCollections.observableList(requisitingGoodsList);
        reqGoodsLstTableView.setItems(observableList);

    }


    public List<Requisition> getRequisitionList() {
        return requisitionList;
    }

    public void setRequisitionList(List<Requisition> requisitionList) {
        this.requisitionList = requisitionList;
    }
}
