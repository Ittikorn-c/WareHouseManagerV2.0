package Warehouse.controllers;

import Sale.controllers.DataManager;
import Sale.models.Goods;
import Sale.models.RequisitionGoods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class ShowsOrderPageController {

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

    private List<Goods> goodsList = new ArrayList<Goods>();
    private ObservableList<Goods> goodsObservableList;

    private DataManager dataManager;

    @FXML
    public void initialize() {
        this.columnGoodsID.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id"));
        this.columnGoodsType.setCellValueFactory(new PropertyValueFactory<Goods, String>("type"));
        this.columnGoodsBrand.setCellValueFactory(new PropertyValueFactory<Goods, String>("brand"));
        this.columnGoodsName.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));

        goodsObservableList = FXCollections.observableList(goodsList);
        tableViewGoods.setItems(goodsObservableList);

    }

    public void reTableGoodses(){

        goodsList= new ArrayList<Goods>();
        for (Goods g : dataManager.getGoodses()){
            goodsList.add(g);
        }
        goodsObservableList = FXCollections.observableList(goodsList);
        tableViewGoods.setItems(goodsObservableList);
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
