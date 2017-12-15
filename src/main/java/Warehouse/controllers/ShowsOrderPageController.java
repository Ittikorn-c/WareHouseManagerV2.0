package Warehouse.controllers;

import Sale.controllers.DataManager;
import Sale.models.Goods;
import Sale.models.Requisition;
import Sale.models.RequisitionGoods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowsOrderPageController {

    @FXML
    private Button pickAllBtn, pickBySlcBtn;
    @FXML
    private TableView orderIDTable, reqGoodsLstTableView;
    @FXML
    private TableColumn<Requisition,Integer> orderIDCol;
    @FXML
    private TableColumn<RequisitionGoods,Integer> idCol, quanCol;
    @FXML
    private TableColumn<RequisitionGoods,String> typeCol, brandCol, nameCol;
//    @FXML
//    private TableColumn<RequisitionGoods,Integer> idItemCol, quanItemCol;
//    @FXML
//    private TableColumn<RequisitionGoods,String> nameItemCol;

    private List<Requisition> requisitionList = new ArrayList<Requisition>();
    private List<Requisition> requisitionListBySelected = new ArrayList<Requisition>();
    //    private List<RequisitionGoods> itemsLst = new ArrayList<RequisitionGoods>();
    private List<RequisitionGoods> RequisitionGoodsList = new ArrayList<RequisitionGoods>();
    private ObservableList<Requisition> observableListReq;
    private ObservableList<RequisitionGoods> observableListItems;


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

        //
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

    public void reTableRe(){
        for (Requisition r : dataManager.getRequisitions()){
            if(r.getStatus().equals("available")) {
                requisitionList.add(r);
            }
        }
        observableListReq = FXCollections.observableArrayList(requisitionList);
        orderIDTable.setItems(observableListReq);
    }

    public void showAction(ActionEvent actionEvent) {
        RequisitionGoodsList.clear();
        Requisition req = (Requisition) orderIDTable.getSelectionModel().getSelectedItem();
        System.out.println(req);
        for (RequisitionGoods rg: req.getRequisitionGoodsArrayList()) {
            RequisitionGoods RequisitionGoods = new RequisitionGoods(rg.getId(),rg.getType(),rg.getBrand(),rg.getName(),rg.getAmount());
            RequisitionGoodsList.add(RequisitionGoods);
        }

        observableListItems = FXCollections.observableArrayList(RequisitionGoodsList);
        reqGoodsLstTableView.setItems(observableListItems);

    }

    public void printBySlcAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WareHouse/SampleViewPage.fxml"));

        try {
            stage.setScene(new Scene((Parent) loader.load()));
            SampleView controller = loader.getController();
            controller.setDataManager(this.dataManager);
            /*
            print by selected
             */
            if((Requisition)orderIDTable.getSelectionModel().getSelectedItem()!= null){
                requisitionListBySelected.clear();
                this.requisitionListBySelected.add((Requisition)orderIDTable.getSelectionModel().getSelectedItem());
                System.out.println(this.requisitionListBySelected);
                controller.setRequisitionList(requisitionListBySelected);

                controller.reTableReq();

            }
            stage.setTitle("Overview");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printAllAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WareHouse/SampleViewPage.fxml"));

        try {
            stage.setScene(new Scene((Parent) loader.load()));
            SampleView controller = loader.getController();
            controller.setDataManager(this.dataManager);
            controller.setRequisitionList(requisitionList);
            System.out.println(requisitionList);
            controller.reTableReq();

            stage.setTitle("Overview");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

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
