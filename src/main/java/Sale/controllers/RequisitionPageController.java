package Sale.controllers;

import Sale.dataSources.DBConnector;
import Sale.models.Goods;
import Sale.models.Requisition;
import Sale.models.RequisitionGoods;

import common.ComboBoxAutoComplete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequisitionPageController {

    @FXML
    private ComboBox<String> typeComboBox, brandComboBox;
    @FXML
    private TextField nameTextField;

    @FXML
    private TableView tableViewGoods;
    @FXML
    private TableColumn<Goods, Integer> columnGoodsID;
    @FXML
    private TableColumn<Goods, String> columnGoodsType;
    @FXML
    private TableColumn<Goods, String> columnGoodsBrand;
    @FXML
    private TableColumn<Goods, String> columnGoodsName;

    @FXML
    private TableView tableViewReq;
    @FXML
    private TableColumn<RequisitionGoods, Integer> columnReqID;
    @FXML
    private TableColumn<RequisitionGoods, String> columnReqType;
    @FXML
    private TableColumn<RequisitionGoods, String> columnReqBrand;
    @FXML
    private TableColumn<RequisitionGoods, String> columnReqName;
    @FXML
    private TableColumn<RequisitionGoods, Integer> columnReqAmount;



    private List<Goods> goodsList = new ArrayList<Goods>();
    private ObservableList<Goods> goodsObservableList;

    private List<RequisitionGoods> requisitionGoodsList = new ArrayList<RequisitionGoods>();
    private ObservableList<RequisitionGoods> requisitionGoodsObservableList;

    private DataManager dataManager;


    @FXML
    public void initialize() {

        typeComboBox.getItems().addAll("A", "ab", "bb", "ca", "dd","aaa");
        String[] s = {"D","E"};
        for (String ss : s){
            typeComboBox.getItems().add(ss);
        }


        new ComboBoxAutoComplete<String>(typeComboBox);

        this.columnGoodsID.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id"));
        this.columnGoodsType.setCellValueFactory(new PropertyValueFactory<Goods, String>("type"));
        this.columnGoodsBrand.setCellValueFactory(new PropertyValueFactory<Goods, String>("brand"));
        this.columnGoodsName.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));

        goodsObservableList = FXCollections.observableList(goodsList);
        tableViewGoods.setItems(goodsObservableList);

        this.columnReqID.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("id"));
        this.columnReqType.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("type"));
        this.columnReqBrand.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("brand"));
        this.columnReqName.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("name"));
        this.columnReqAmount.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("amount"));

        requisitionGoodsObservableList = FXCollections.observableList(requisitionGoodsList);
        tableViewReq.setItems(requisitionGoodsObservableList);
    }

    public void reTableGoodses(){

        goodsList= new ArrayList<Goods>();
        for (Goods g : dataManager.getGoodses()){
            goodsList.add(g);
        }
        goodsObservableList = FXCollections.observableList(goodsList);
        tableViewGoods.setItems(goodsObservableList);
    }

    public void clearAll(ActionEvent actionEvent) {
        requisitionGoodsList.clear();

        requisitionGoodsObservableList = FXCollections.observableList(requisitionGoodsList);
        tableViewReq.setItems(requisitionGoodsObservableList);
    }

    public void addGoods(ActionEvent actionEvent) {
        Goods good = (Goods) tableViewGoods.getSelectionModel().getSelectedItem();
        RequisitionGoods requisitionGoods = new RequisitionGoods(good.getId(), good.getType(), good.getBrand(), good.getName(), 0);

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Sale/AddGoodsAmountPage.fxml"));

        try {
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Add Goods Amount");

            AddAmountController controller = loader.getController();
            controller.getIdLabel().setText(requisitionGoods.getId()+"");
            controller.getTypeLabel().setText(requisitionGoods.getType());
            controller.getBrandLabel().setText(requisitionGoods.getBrand());
            controller.getNameLabel().setText(requisitionGoods.getName());
            controller.setRequisitionGoods(requisitionGoods);

            stage.showAndWait();

            for(Goods g : dataManager.getGoodses()){
                System.out.println(String.format("id: %d Quantity %d",g.getId(), g.getQuantity()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        requisitionGoodsList.add(requisitionGoods);
        requisitionGoodsObservableList = FXCollections.observableList(requisitionGoodsList);
        tableViewReq.setItems(requisitionGoodsObservableList);
    }

    public void removeGoods(ActionEvent actionEvent) {
        requisitionGoodsList.remove(tableViewReq.getSelectionModel().getSelectedItem());
        requisitionGoodsObservableList = FXCollections.observableList(requisitionGoodsList);
        tableViewReq.setItems(requisitionGoodsObservableList);
    }

    public void addNewGoods(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Sale/AddNewGoodsPage.fxml"));

        try {
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Add New Goods");

            AddNewGoodsController controller = loader.getController();
            controller.setDataManager(dataManager);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveRequisition(ActionEvent actionEvent) {
        ArrayList<RequisitionGoods> requisitionGoodsList = (ArrayList<RequisitionGoods>) this.requisitionGoodsList;
        String status = calRequisitionStatus(requisitionGoodsList);

        Requisition requisition = new Requisition(0,status, requisitionGoodsList);
        dataManager.insertRequisition(requisition);
    }

    public String calRequisitionStatus(ArrayList<RequisitionGoods> requisitionGoodsList){
        String status = "no";
        int numReGoods = requisitionGoodsList.size();
        int temp = 0;
        for(RequisitionGoods rg : requisitionGoodsList){
            for(Goods g : dataManager.getGoodses()){
                if (rg.getAmount() <= g.getQuantity()){
                    temp++;
                }
            }
        }

        if(numReGoods == temp){
            status = "available";
        }

        return status;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
