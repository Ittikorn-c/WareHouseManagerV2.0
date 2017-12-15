package Warehouse.controllers;

import Sale.controllers.DataManager;
import Sale.models.Requisition;
import Sale.models.RequisitionGoods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class ReqConfirmation {

    @FXML
    private TableView orderIDTable;
    @FXML
    private TableColumn<Requisition, Integer> orderIDCol;

    @FXML
    private TableView reqGoodsLstTableView;
    @FXML
    private TableColumn<RequisitionGoods, Integer> idCol, quanCol;
    @FXML
    private TableColumn<RequisitionGoods, String> typeCol, brandCol, nameCol;

    private List<Requisition> requisitionList = new ArrayList<Requisition>();
    private ObservableList<Requisition> observableListReq;

    private List<RequisitionGoods> RequisitionGoodsList = new ArrayList<RequisitionGoods>();
    private ObservableList<RequisitionGoods> observableListItems;

    private DataManager dataManager;

    private Requisition selectingReq;

    @FXML
    private void initialize(){
        this.orderIDCol.setCellValueFactory(new PropertyValueFactory<Requisition, Integer>("id"));

        observableListReq = FXCollections.observableArrayList(requisitionList);
        orderIDTable.setItems(observableListReq);

        this.idCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("id"));
        this.typeCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("type"));
        this.brandCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("brand"));
        this.nameCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("name"));
        this.quanCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("amount"));

        observableListItems = FXCollections.observableArrayList(RequisitionGoodsList);
        reqGoodsLstTableView.setItems(observableListItems);
    }

    public void reTableRe(){
        for (Requisition r : dataManager.getRequisitions()){
            if (r.getStatus().equals("กำลังเบิก"))
                requisitionList.add(r);
        }
        observableListReq = FXCollections.observableArrayList(requisitionList);
        orderIDTable.setItems(observableListReq);
    }

    @FXML
    public void confirmHandle() {
        if (selectingReq != null) {
            selectingReq.setStatus("เบิกแล้ว");
            reTableRe();
        }
    }

    @FXML
    public void showAction(ActionEvent actionEvent) {
        selectingReq = (Requisition) reqGoodsLstTableView.getSelectionModel().getSelectedItem();
        for (RequisitionGoods rg: selectingReq.getRequisitionGoodsArrayList()) {
            RequisitionGoods RequisitionGoods = new RequisitionGoods(rg.getId(),rg.getType(),rg.getBrand(),rg.getName(),rg.getAmount());
            RequisitionGoodsList.add(RequisitionGoods);
        }

        observableListItems = FXCollections.observableArrayList(RequisitionGoodsList);
        reqGoodsLstTableView.setItems(observableListItems);
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
