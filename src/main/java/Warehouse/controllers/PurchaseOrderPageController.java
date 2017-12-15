package Warehouse.controllers;

import Sale.controllers.DataManager;
import Sale.models.Goods;
import Sale.models.PurchaseOrder;
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
    public List<Goods> listOrder = new ArrayList();
    public ObservableList<Goods> observableListOrder;
    public List<Goods> listImportantToOrder = new ArrayList();
    public ObservableList<Goods> observableListImportantToOrder;
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
    TableColumn<Goods, Integer> columnImportantToOrderID;
    @FXML
    TableColumn<Goods, String> columnImportantToOrderType;
    @FXML
    TableColumn<Goods, String> columnImportantToOrderBrand;
    @FXML
    TableColumn<Goods, String> columnImportantToOrderName;
    @FXML
    TableColumn<Goods, Integer> columnImportantToOrderAmount;
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

        this.columnImportantToOrderID.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id"));
        this.columnImportantToOrderType.setCellValueFactory(new PropertyValueFactory<Goods, String>("type"));
        this.columnImportantToOrderBrand.setCellValueFactory(new PropertyValueFactory<Goods, String>("brand"));
        this.columnImportantToOrderName.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
        this.columnImportantToOrderAmount.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("amount"));

        observableListImportantToOrder = FXCollections.observableArrayList(listImportantToOrder);
        tableViewImportantToOrder.setItems(observableListOrder);
    }


    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
        for(Goods g : dataManager.getGoodses()){
            listGoods.add(g);
        }
        /*for(Goods g : dataManager.getNotEnoughGoodses()){
            listImportantToOrder.add(g);
        }*/
        observableListGoods = FXCollections.observableArrayList(listGoods);
        tableViewGoods.setItems(observableListGoods);
        observableListGoods = FXCollections.observableArrayList(listImportantToOrder);
        tableViewGoods.setItems(observableListImportantToOrder);

    }

    @FXML
    public void addOrder(){
        Goods g = (Goods) this.tableViewGoods.getSelectionModel().getSelectedItem();
        Goods order = new Goods(g.getId(), g.getType(), g.getBrand(), g.getName(), g.getQuantity());

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PurchaseOrderResources/AddAmountPage.fxml"));

        try {
            stage.setScene(new Scene((Parent) loader.load()));
            AddAmountOnPurchasePageController controller = loader.getController();
            controller.idLabel.setText(String.valueOf(g.getId()));
            controller.typeLabel.setText(g.getType());
            controller.brandLabel.setText(g.getBrand());
            controller.nameLabel.setText(g.getName());
            controller.goods = order;
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
        listOrder = new ArrayList<Goods>();
        observableListOrder = FXCollections.observableArrayList(listOrder);
        tableViewOrder.setItems(observableListOrder);
    }

    @FXML
    public void saveOrder(){
        PurchaseOrder po = new PurchaseOrder((ArrayList<Goods>) listOrder);
        //this.dataManager.insertPurchaseOrder(pr);

    }
}
