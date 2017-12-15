package Warehouse.controllers;

import Sale.controllers.DataManager;
import Sale.models.Requisition;
import Sale.models.RequisitionGoods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowOrderPageController {

    private DataManager dataManager;
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
            requisitionList.add(r);
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

            controller.setRequisitionList(requisitionList);
            System.out.println(requisitionList);
            controller.reTableReq();

            stage.setTitle("Overview");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

}
