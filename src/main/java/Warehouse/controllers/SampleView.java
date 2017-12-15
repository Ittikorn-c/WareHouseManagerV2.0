package Warehouse.controllers;


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
    @FXML
    private TableView reqGoodsLstTableView;
    @FXML
    private TableColumn<RequisitionGoods,Integer> idCol, quanCol;
    @FXML
    private TableColumn<RequisitionGoods,String> typeCol, brandCol, nameCol;


    public SampleView() {
    }
    @FXML
    public void initialize(){
        for (Requisition r : requisitionList) {
            for (RequisitionGoods req: r.getRequisitionGoodsArrayList()) {
                for (RequisitionGoods reqTing :RequisitionGoodsList) {
                    if(req.getId() == reqTing.getId()){
                        reqTing.setAmount(req.getAmount()+ reqTing.getAmount());
                        break;
                    }
                    RequisitionGoodsList.add(new RequisitionGoods(req.getId(), req.getType(), req.getBrand(),req.getName(),req.getAmount()));

                }

            }
        }
        this.idCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("id"));
        this.typeCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("type"));
        this.brandCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("brand"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("name"));
        this.quanCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("amount"));

        observableList = FXCollections.observableList(RequisitionGoodsList);
        reqGoodsLstTableView.setItems(observableList);

    }


    public List<Requisition> getRequisitionList() {
        return requisitionList;
    }

    public void setRequisitionList(List<Requisition> requisitionList) {
        this.requisitionList = requisitionList;
    }
}
