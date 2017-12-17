package Sale.controllers;

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

    private ArrayList<String> allTypes;
    private ArrayList<String> allBrands;


    @FXML
    public void initialize() {

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

    public void initComboBox() {
        allTypes = dataManager.getAllTypes();
        allBrands = dataManager.getAllBrands();
        typeComboBox.getItems().addAll(allTypes);
        brandComboBox.getItems().addAll(allBrands);

        typeComboBox.setValue("");
        brandComboBox.setValue("");

//        typeComboBox.setOnKeyTyped(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                filterTypeKeyType();
//            }
//        });
//
//        brandComboBox.setOnKeyTyped(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                filterBrandKeyType();
//            }
//        });
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

        } catch (IOException e) {
            e.printStackTrace();
        }


        if(requisitionGoods.getAmount()!=0){
            int i=0;

            for (RequisitionGoods g : requisitionGoodsList) {
                if (g.getId() == requisitionGoods.getId()){
                    g.setAmount(g.getAmount()+requisitionGoods.getAmount());
                    i++;
                    tableViewReq.refresh();
                    break;
                }


            }
            if(i==0){
                requisitionGoodsList.add(requisitionGoods);
                requisitionGoodsObservableList = FXCollections.observableList(requisitionGoodsList);
                tableViewReq.setItems(requisitionGoodsObservableList);
            }
        }

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

            reTableGoodses();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveRequisition(ActionEvent actionEvent) {
        if(!requisitionGoodsList.isEmpty()) {
            ArrayList<RequisitionGoods> requisitionGoodsList = (ArrayList<RequisitionGoods>) this.requisitionGoodsList;
            String status = calRequisitionStatus(requisitionGoodsList);
            System.out.println(status);
            Requisition requisition = new Requisition(0, status, requisitionGoodsList);
            dataManager.insertRequisition(requisition);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "saved");
            alert.showAndWait();
            requisitionGoodsList.clear();

            requisitionGoodsObservableList = FXCollections.observableList(requisitionGoodsList);
            tableViewReq.setItems(requisitionGoodsObservableList);
        }
    }

    public String calRequisitionStatus(ArrayList<RequisitionGoods> requisitionGoodsList){
        String status = "no";
        int numReGoods = requisitionGoodsList.size();
        int temp = 0;
        for(RequisitionGoods rg : requisitionGoodsList){
            for(Goods g : dataManager.getGoodses()){
                if (rg.getId() == g.getId() && rg.getAmount() <= g.getQuantity()){
                    temp++;
                }
            }
        }
        System.out.println(numReGoods);
        System.out.println(temp);
        if(numReGoods == temp){
            status = "available";         // ของมีแต่ status no
        }

        return status;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

//    public void filterTypeKeyType() {
//        System.out.println("key type type");
//        ArrayList<String> filtered = new ArrayList<>();
//
//        for (String type : allTypes) {
//            if (type.contains(typeComboBox.getValue())) {
//                filtered.add(type);
//            }
//        }
//
//        typeComboBox.getItems().setAll(filtered);
//    }
//
//    public void filterBrandKeyType() {
//        System.out.println("key brand type");
//        ArrayList<String> filtered = new ArrayList<>();
//
//        for (String brand : allBrands) {
//            if (brand.contains(brandComboBox.getValue())) {
//                filtered.add(brand);
//            }
//        }
//
//        brandComboBox.getItems().setAll(filtered);
//    }

    List<Goods> goodsByType = new ArrayList<>();
    @FXML
    public void filterTypeSelect() {
        goodsByType.clear();
        nameTextField.clear();

        brandComboBox.getItems().clear();
        brandComboBox.getItems().addAll(dataManager.getAllBrands(typeComboBox.getValue()));
        brandComboBox.setDisable(false);

        for (Goods g : goodsList) {
            if (typeComboBox.getValue().equals(g.getType()))
                goodsByType.add(g);
        }

        goodsObservableList = FXCollections.observableList(goodsByType);
        tableViewGoods.setItems(goodsObservableList);
    }

    List<Goods> goodsByBrand = new ArrayList<>();
    @FXML
    public void filterBrandSelect() {
        goodsByBrand.clear();
        nameTextField.clear();

        for (Goods g : goodsByType) {
            if (brandComboBox.getValue().equals(g.getBrand()))
                goodsByBrand.add(g);
        }

        goodsObservableList = FXCollections.observableList(goodsByBrand);
        tableViewGoods.setItems(goodsObservableList);
    }

    List<Goods> goodsByName = new ArrayList<>();
    @FXML
    public void filterNameType() {
        goodsByName.clear();

        for (Goods g : goodsByBrand) {
            if (g.getName().contains(nameTextField.getText()))
                goodsByName.add(g);
        }

        goodsObservableList = FXCollections.observableList(goodsByName);
        tableViewGoods.setItems(goodsObservableList);
    }
}
