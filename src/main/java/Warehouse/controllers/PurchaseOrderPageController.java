package Warehouse.controllers;

import Sale.controllers.DataManager;
import Sale.models.Goods;
import Sale.models.Requisition;
import Sale.models.RequisitionGoods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class PurchaseOrderPageController {
    private DataManager dataManager;
    public List<Goods> listGoods = new ArrayList();
    public ObservableList<Goods> observableListGoods;
    public List<RequisitionGoods> listOrder = new ArrayList();
    public ObservableList<RequisitionGoods> observableListOrder;
    public List<RequisitionGoods> listImportantToOrder = new ArrayList();
    public List<RequisitionGoods> importantToOrderlist = new ArrayList();
    public ObservableList<RequisitionGoods> observableListImportantToOrder;
    @FXML
    TableView tableViewGoods;
    @FXML
    TableColumn<Goods, Integer> columnGoodsID;
    @FXML
    TableColumn<Goods, String> columnGoodsType;
    @FXML
    TableColumn<Goods, String> columnGoodsBrand;
    @FXML
    TableColumn<Goods, String> columnGoodsName;
    @FXML
    TableView tableViewOrder;
    @FXML
    TableColumn<Goods, Integer> columnOrderID;
    @FXML
    TableColumn<Goods, String> columnOrderType;
    @FXML
    TableColumn<Goods, String> columnOrderBrand;
    @FXML
    TableColumn<Goods, String> columnOrderName;
    @FXML
    TableColumn<Goods, Integer> columnOrderAmount;
    @FXML
    TableView tableViewImportantToOrder;
    @FXML
    TableColumn<RequisitionGoods, Integer> columnImportantToOrderID;
    @FXML
    TableColumn<RequisitionGoods, String> columnImportantToOrderType;
    @FXML
    TableColumn<RequisitionGoods, String> columnImportantToOrderBrand;
    @FXML
    TableColumn<RequisitionGoods, String> columnImportantToOrderName;
    @FXML
    TableColumn<RequisitionGoods, Integer> columnImportantToOrderAmount;
    @FXML
    ComboBox<String> typeComboBox;
    @FXML
    ComboBox<String> brandComboBox;
    @FXML
    TextField nameTextField;

    @FXML
    public void initialize(){

        typeComboBox.getItems().addAll("A", "ab", "bb", "ca", "dd","aaa");
        String[] s = {"D","E"};
        for (String ss : s){
            typeComboBox.getItems().add(ss);
        }





        this.columnGoodsID.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id"));
        this.columnGoodsType.setCellValueFactory(new PropertyValueFactory<Goods, String>("type"));
        this.columnGoodsBrand.setCellValueFactory(new PropertyValueFactory<Goods, String>("brand"));
        this.columnGoodsName.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));

        observableListGoods = FXCollections.observableArrayList(listGoods);
        tableViewGoods.setItems(observableListGoods);

        this.columnOrderID.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id"));
        this.columnOrderType.setCellValueFactory(new PropertyValueFactory<Goods, String>("type"));
        this.columnOrderBrand.setCellValueFactory(new PropertyValueFactory<Goods, String>("brand"));
        this.columnOrderName.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
        this.columnOrderAmount.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("amount"));

        observableListOrder = FXCollections.observableArrayList(listOrder);
        tableViewOrder.setItems(observableListOrder);

        this.columnImportantToOrderID.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("id"));
        this.columnImportantToOrderType.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("type"));
        this.columnImportantToOrderBrand.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("brand"));
        this.columnImportantToOrderName.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, String>("name"));
        this.columnImportantToOrderAmount.setCellValueFactory(new PropertyValueFactory<RequisitionGoods, Integer>("amount"));

        observableListImportantToOrder = FXCollections.observableArrayList(listImportantToOrder);
        tableViewImportantToOrder.setItems(observableListOrder);
    }


    public void reTableGoods() {
        for(Goods g : dataManager.getGoodses()){
            listGoods.add(g);
        }

        observableListGoods = FXCollections.observableArrayList(listGoods);
        tableViewGoods.setItems(observableListGoods);

    }

    public void reTableImp(){
        for (Requisition r : dataManager.getRequisitions()){
            if(r.getStatus().equals("no")) {
                for (RequisitionGoods rg : r.getRequisitionGoodsArrayList()) {
                    listImportantToOrder.add(rg);
                }
            }
        }

        ArrayList<Integer> num = new ArrayList<Integer>();

        for (RequisitionGoods reqTing :listImportantToOrder) {
            if (!num.contains(reqTing.getId())){
                num.add(reqTing.getId());
                importantToOrderlist.add(reqTing);
            }
            else{
                for(RequisitionGoods g : importantToOrderlist){
                    if(g.getId() == reqTing.getId()){
                        g.setAmount(g.getAmount()+reqTing.getAmount());
                    }
                }
            }
        }

        for (RequisitionGoods rg : importantToOrderlist){
            for (Goods g : dataManager.getGoodses()){
                if (rg.getId() == g.getId()){
                    if (rg.getAmount() > g.getQuantity()){
                        int result = Math.abs(rg.getAmount() - g.getQuantity());
                        rg.setAmount(result);
                    }
                    else{
                        rg.setAmount(0);
                    }
                }
            }
        }
        int i = 0;
        while (i<importantToOrderlist.size()) {
            if(importantToOrderlist.get(i).getAmount()==0){
                importantToOrderlist.remove(i);
            }
            else{
                i++;
            }
        }
        observableListImportantToOrder = FXCollections.observableArrayList(importantToOrderlist);
        tableViewImportantToOrder.setItems(observableListImportantToOrder);
    }

    @FXML
    public void addOrder(){
        Goods g = (Goods) this.tableViewGoods.getSelectionModel().getSelectedItem();
        RequisitionGoods order = new RequisitionGoods(g.getId(), g.getType(), g.getBrand(), g.getName(), g.getQuantity());

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Warehouse/AddAmountPage.fxml"));

        try {
            stage.setScene(new Scene((Parent) loader.load()));
            AddAmountOnPurchasePageController controller = loader.getController();
            controller.idLabel.setText(String.valueOf(g.getId()));
            controller.typeLabel.setText(g.getType());
            controller.brandLabel.setText(g.getBrand());
            controller.nameLabel.setText(g.getName());
            controller.setGoods(order);
            stage.setTitle("Appointment list");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }


        this.listOrder.add(order);
        observableListOrder = FXCollections.observableArrayList(listOrder);
        tableViewOrder.setItems(observableListOrder);

    }

    @FXML
    public void deleteOrder(){
        System.out.println(listOrder);
        System.out.println(this.tableViewOrder.getSelectionModel().getSelectedItem());
        listOrder.remove(this.tableViewOrder.getSelectionModel().getSelectedItem());

        observableListOrder = FXCollections.observableArrayList(listOrder);
        tableViewOrder.setItems(observableListOrder);
    }

    @FXML
    public void cancelOrder(){
        listOrder = new ArrayList<RequisitionGoods>();
        observableListOrder = FXCollections.observableArrayList(listOrder);
        tableViewOrder.setItems(observableListOrder);
    }

    @FXML
    public void saveOrder(){
//        PurchaseOrder po = new PurchaseOrder((ArrayList<Goods>) listOrder);
//        //this.dataManager.insertPurchaseOrder(pr);

    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
