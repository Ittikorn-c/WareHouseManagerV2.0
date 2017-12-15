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
    private TableView reqIDTable;
    @FXML
    private TableColumn<Requisition, Integer> reqIDCol;

    @FXML
    private TableView itemsTable;
    @FXML
    private TableColumn<RequisitionGoods, Integer> itemsIDCol, itemQuanCol;
    @FXML
    private TableColumn<RequisitionGoods, String> itemTypeCol, itemBrandCol, itemNameCol;

    private List<Requisition> confirmReqList = new ArrayList<Requisition>();
    private ObservableList<Requisition> confirmReqObservableList;

    private List<RequisitionGoods> confirmItemsList = new ArrayList<RequisitionGoods>();
    private ObservableList<RequisitionGoods> confirmItemsObservableList;

    private DataManager dataManager;

    private Requisition selectingReq;

    @FXML
    private void initialize(){
        this.reqIDCol.setCellValueFactory(new PropertyValueFactory<Requisition, Integer>("id"));

        confirmReqObservableList = FXCollections.observableArrayList(confirmReqList);
        reqIDTable.setItems(confirmReqObservableList);

        this.itemsIDCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("id"));
        this.itemTypeCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("type"));
        this.itemBrandCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("brand"));
        this.itemNameCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("name"));
        this.itemQuanCol.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("amount"));

        confirmItemsObservableList = FXCollections.observableArrayList(confirmItemsList);
        itemsTable.setItems(confirmItemsObservableList);
    }

    public void reTableConfirm(){
        confirmReqList.clear();
        for (Requisition r : dataManager.getRequisitions()){
            if (r.getStatus().equals("picking"))
                confirmReqList.add(r);
        }
        confirmReqObservableList = FXCollections.observableArrayList(confirmReqList);
        reqIDTable.setItems(confirmReqObservableList);
    }

    @FXML
    public void confirmHandle() {
        if (selectingReq != null) {
            selectingReq.setStatus("done");
            dataManager.setRequisitionStatus(selectingReq.getId(), "done");
            reTableConfirm();
        }
    }

    @FXML
    public void showConfirm(ActionEvent actionEvent) {
        confirmItemsList.clear();
        selectingReq = (Requisition) reqIDTable.getSelectionModel().getSelectedItem();
        System.out.println(selectingReq);
        for (RequisitionGoods rg: selectingReq.getRequisitionGoodsArrayList()) {
            RequisitionGoods RequisitionGoods = new RequisitionGoods(rg.getId(),rg.getType(),rg.getBrand(),rg.getName(),rg.getAmount());
            confirmItemsList.add(RequisitionGoods);
        }

        confirmItemsObservableList = FXCollections.observableArrayList(confirmItemsList);
        itemsTable.setItems(confirmItemsObservableList);
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
