package Warehouse.controllers;

import Sale.controllers.DataManager;
import Sale.models.*;
import Warehouse.models.PurchaseOrder;
import Warehouse.models.PurchaseOrderGoods;
import Warehouse.models.Supplier;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ShowsOrderPageController {

    /* If */
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


    /* Nuiye */
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

    /* Mhoo */
    public List<Goods> listGoods = new ArrayList();
    public ObservableList<Goods> observableListGoods;
    public List<PurchaseOrderGoods> listOrder = new ArrayList();
    public ObservableList<PurchaseOrderGoods> observableListOrder;
    public List<RequisitionGoods> listImportantToOrder = new ArrayList();
    public List<RequisitionGoods> importantToOrderlist = new ArrayList();
    public ObservableList<RequisitionGoods> observableListImportantToOrder;
    @FXML
    TableView poTableViewGoods;
    @FXML
    TableColumn<Goods, Integer> poColumnGoodsID;
    @FXML
    TableColumn<Goods, String> poColumnGoodsType;
    @FXML
    TableColumn<Goods, String> poColumnGoodsBrand;
    @FXML
    TableColumn<Goods, String> poColumnGoodsName;
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
    ComboBox<String> typeComboBox, brandComboBox, supComboBox;
    @FXML
    TextField nameTextField;

    /* Petch */
    @FXML
    private TableView tableViewGoods;
    @FXML
    private TableColumn<Goods, Integer> columnGoodsID;
    @FXML
    private TableColumn<Goods, String> columnGoodsType, columnGoodsBrand, columnGoodsName;

    private List<Goods> petchList = new ArrayList<Goods>();
    private ObservableList<Goods> petchObservableList;

    /* Petch new PO */
    @FXML
    private TableView poTableView;
    @FXML
    private TableColumn<PurchaseOrder, Integer> poIDCol;

    @FXML
    private TableView poItemTableView;
    @FXML
    private TableColumn<PurchaseOrderGoods, Integer> poItemIdCol, poItemQuanCol;
    @FXML
    private TableColumn<PurchaseOrderGoods, String> poItemTypeCol, poItemBrandCol, poItemNameCol;

    private List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
    private ObservableList<PurchaseOrder> poObservableList;

    private List<PurchaseOrderGoods> poGoodsList = new ArrayList<PurchaseOrderGoods>();
    private ObservableList<PurchaseOrderGoods> poGoodsObservableList;

    /* Petch sup */
    @FXML
    private TableView supTable;
    @FXML
    private TableColumn<Supplier, Integer> supIDCol;
    @FXML
    private TableColumn<Supplier, String> namesupCol;
    @FXML
    private TableColumn<Supplier, String> addrIDCol;
    @FXML
    private TableColumn<Supplier, String> phoneCol;

    private List<Supplier> supList = new ArrayList<>();
    private ObservableList<Supplier> observableListSup;

    private DataManager dataManager;

    private Requisition selectingReq;

    @FXML
    private Label dateLabel1, dateLabel2, dateLabel3, dateLabel4;


    @FXML
    private void initialize(){

        dateLabel1.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        dateLabel2.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        dateLabel3.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        dateLabel4.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        /* If */
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

        /* Nuiye */
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

        /* Mhoo */

        this.poColumnGoodsID.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id"));
        this.poColumnGoodsType.setCellValueFactory(new PropertyValueFactory<Goods, String>("type"));
        this.poColumnGoodsBrand.setCellValueFactory(new PropertyValueFactory<Goods, String>("brand"));
        this.poColumnGoodsName.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));

        observableListGoods = FXCollections.observableArrayList(listGoods);
        poTableViewGoods.setItems(observableListGoods);

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
        tableViewImportantToOrder.setItems(observableListImportantToOrder);

        /* Petch */
//        this.columnGoodsID.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id"));
//        this.columnGoodsType.setCellValueFactory(new PropertyValueFactory<Goods, String>("type"));
//        this.columnGoodsBrand.setCellValueFactory(new PropertyValueFactory<Goods, String>("brand"));
//        this.columnGoodsName.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
//
//        petchObservableList = FXCollections.observableList(petchList);
//        tableViewGoods.setItems(petchObservableList);

//        /* Petch new PO */
        this.poIDCol.setCellValueFactory(new PropertyValueFactory<PurchaseOrder, Integer>("id"));

        poObservableList = FXCollections.observableList(poList);
        poTableView.setItems(poObservableList);

        this.poItemIdCol.setCellValueFactory(new PropertyValueFactory<PurchaseOrderGoods, Integer>("id"));
        this.poItemTypeCol.setCellValueFactory(new PropertyValueFactory<PurchaseOrderGoods, String>("type"));
        this.poItemBrandCol.setCellValueFactory(new PropertyValueFactory<PurchaseOrderGoods, String>("brand"));
        this.poItemNameCol.setCellValueFactory(new PropertyValueFactory<PurchaseOrderGoods, String>("name"));
        this.poItemQuanCol.setCellValueFactory(new PropertyValueFactory<PurchaseOrderGoods, Integer>("amount"));

        poGoodsObservableList = FXCollections.observableList(poGoodsList);
        poItemTableView.setItems(poGoodsObservableList);

        /* Petch sup */

        this.supIDCol.setCellValueFactory(new PropertyValueFactory<Supplier, Integer>("id"));
        this.namesupCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        this.addrIDCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("addr"));
        this.phoneCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("phone"));

        observableListSup = FXCollections.observableArrayList(supList);
        supTable.setItems(observableListSup);

    }

    public void rePoIdTable() {
        poList.clear();
        for (PurchaseOrder po : dataManager.getAllPurchases()) {
            if (po.getStatus().equals("waiting")) {
                poList.add(po);
            }
        }
        poObservableList = FXCollections.observableList(poList);
        poTableView.setItems(poObservableList);
    }
    private PurchaseOrder purchaseOrder;
    @FXML
    public void addPOItem() {
        if(purchaseOrder!= null) {
            purchaseOrder.setStatus("done");
            dataManager.setPurchaseOrderStatus(purchaseOrder.getId(),"done");
            for(PurchaseOrderGoods g : purchaseOrder.getRequisitionGoodsArrayList()){
                for (Goods gg : dataManager.getGoodses()){
                    if(g.getId() == gg.getId()){
                        dataManager.updateGoodsAmount(g.getId(),g.getAmount()+gg.getQuantity());
                    }
                }

            }
        }
        reTableGoods();
        supList.clear();
        observableListSup = FXCollections.observableList(supList);
        supTable.setItems(observableListSup);

        poGoodsList.clear();
        poGoodsObservableList = FXCollections.observableList(poGoodsList);
        poItemTableView.setItems(poGoodsObservableList);
        rePoIdTable();
        reTableImp();
        calStatusRequisition();
    }
    @FXML
    public void showPOItem(ActionEvent actionEvent) {
        poGoodsList.clear();
        supList.clear();
        purchaseOrder = (PurchaseOrder) this.poTableView.getSelectionModel().getSelectedItem();
        for(PurchaseOrderGoods g : purchaseOrder.getRequisitionGoodsArrayList()){
            poGoodsList.add(g);
        }

        poGoodsObservableList = FXCollections.observableList(poGoodsList);
        poItemTableView.setItems(poGoodsObservableList);

        supList.add(purchaseOrder.getSupplier());
        observableListSup = FXCollections.observableList(supList);
        supTable.setItems(observableListSup);
    }
//    public void reTablePetch() {
//        for(Goods g : dataManager.getGoodses()){
//            petchList.add(g);
//        }
//
//        petchObservableList = FXCollections.observableArrayList(petchList);
//        tableViewGoods.setItems(petchObservableList);
//    }

//    @FXML
//    public void addPetch() {
//        Goods g = (Goods) this.tableViewGoods.getSelectionModel().getSelectedItem();
//
//        Stage stage = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Warehouse/AddAmountPage.fxml"));
//
//        try {
//            stage.setScene(new Scene((Parent) loader.load()));
//            AddAmountOnPurchasePageController controller = loader.getController();
//            controller.idLabel.setText(String.valueOf(g.getId()));
//            controller.typeLabel.setText(g.getType());
//            controller.brandLabel.setText(g.getBrand());
//            controller.nameLabel.setText(g.getName());
//            controller.setGoods(g);
//            stage.setTitle("Add Goods");
//            stage.showAndWait();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        dataManager.updateGoods(g);
//
//        reTablePetch();
//        reTableGoods();
//    }

    public void reTableRe(){
        requisitionList.clear();
        for (Requisition r : dataManager.getRequisitions()){
            if(r.getStatus().equals("available")) {
                requisitionList.add(r);
            }
        }
        observableListReq = FXCollections.observableArrayList(requisitionList);
        orderIDTable.setItems(observableListReq);

        RequisitionGoodsList.clear();
        observableListItems = FXCollections.observableArrayList(RequisitionGoodsList);
        reqGoodsLstTableView.setItems(observableListItems);
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

            reTableRe();
            reTableConfirm();

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

            reTableRe();
            reTableConfirm();

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
    private Button confirmBtn;
    @FXML
    public void confirmHandle() {
        if (selectingReq != null) {
            selectingReq.setStatus("done");
            dataManager.setRequisitionStatus(selectingReq.getId(), "done");
            for (RequisitionGoods rq : selectingReq.getRequisitionGoodsArrayList()) {
                for(Goods goods : dataManager.getGoodses()){
                    if(rq.getId() == goods.getId()){
                        dataManager.updateGoodsAmount(rq.getId(),Math.abs(goods.getQuantity()-rq.getAmount()));
                    }
                }
            }
            reTableConfirm();


            confirmReqObservableList = FXCollections.observableArrayList(confirmReqList);
            reqIDTable.setItems(confirmReqObservableList);

            confirmItemsList.clear();
            confirmItemsObservableList = FXCollections.observableArrayList(confirmItemsList);
            itemsTable.setItems(confirmItemsObservableList);

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

    public void reTableGoods() {
        listGoods.clear();

        for(Goods g : dataManager.getGoodses()){
            listGoods.add(g);
        }

        observableListGoods = FXCollections.observableArrayList(listGoods);
        poTableViewGoods.setItems(observableListGoods);

    }

    private ArrayList<String> allTypes;
    private ArrayList<String> allBrands;
    private ArrayList<String> supplierNames;

    public void initComboBox() {
        allTypes = dataManager.getAllTypes();
        allBrands = dataManager.getAllBrands();
        supplierNames = dataManager.getAllSupNames();
        typeComboBox.getItems().setAll(allTypes);
        brandComboBox.getItems().setAll(allBrands);
        supComboBox.getItems().setAll(supplierNames);

        typeComboBox.setValue("");
        brandComboBox.setValue("");
        supComboBox.setValue("");
    }
    List<Goods> goodsByType = new ArrayList<>();
    @FXML
    public void filterTypeSelect() {
        goodsByType.clear();
        nameTextField.clear();

        brandComboBox.getItems().clear();
        brandComboBox.getItems().addAll(dataManager.getAllBrands(typeComboBox.getValue()));
        brandComboBox.setDisable(false);

        for (Goods g : listGoods) {
            if (typeComboBox.getValue().equals(g.getType()))
                goodsByType.add(g);
        }

        observableListGoods = FXCollections.observableList(goodsByType);
        poTableViewGoods.setItems(observableListGoods);
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

        observableListGoods = FXCollections.observableList(goodsByBrand);
        poTableViewGoods.setItems(observableListGoods);;
    }

    List<Goods> goodsByName = new ArrayList<>();
    @FXML
    public void filterNameType() {
        goodsByName.clear();

        for (Goods g : goodsByBrand) {
            if (g.getName().contains(nameTextField.getText()))
                goodsByName.add(g);
        }

        observableListGoods = FXCollections.observableList(goodsByName);
        poTableViewGoods.setItems(observableListGoods);
    }

    public void reTableImp(){
        dataManager.loadAllGoodses();
        listImportantToOrder.clear();
        importantToOrderlist.clear();
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

        ArrayList<PurchaseOrderGoods> purchaseOrderGoodsArrayList = new ArrayList<>();
        for(PurchaseOrder po : dataManager.getAllPurchases()){
            if(po.getStatus().equals("waiting")){
                for(PurchaseOrderGoods pg : po.getRequisitionGoodsArrayList()){
                    purchaseOrderGoodsArrayList.add(pg);
                }
            }
        }
        num = new ArrayList<Integer>();
        ArrayList<PurchaseOrderGoods> purchaseOrderGoodsArrayList1 = new ArrayList<>();
        for (PurchaseOrderGoods pg :purchaseOrderGoodsArrayList) {
            if (!num.contains(pg.getId())){
                num.add(pg.getId());
                purchaseOrderGoodsArrayList1.add(pg);
            }
            else{
                for(PurchaseOrderGoods g : purchaseOrderGoodsArrayList1){
                    if(g.getId() == pg.getId()){
                        g.setAmount(g.getAmount()+pg.getAmount());
                    }
                }
            }
        }

        for(RequisitionGoods r : importantToOrderlist){
            for(PurchaseOrderGoods p : purchaseOrderGoodsArrayList1){
                if(r.getId() == p.getId()){
                    if(r.getAmount() < p.getAmount()){
                        r.setAmount(0);
                    }
                    else{
                        r.setAmount(r.getAmount()-p.getAmount());
                    }
                }
            }
        }

        i = 0;
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
        Goods g = (Goods) this.poTableViewGoods.getSelectionModel().getSelectedItem();
        PurchaseOrderGoods order = new PurchaseOrderGoods(g.getId(), g.getType(), g.getBrand(), g.getName(), g.getQuantity());

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
            stage.setTitle("Add Order");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(order.getAmount()>0 && order.getAmount()<10000){
            if(order.getAmount()!=0){
                int i=0;

                for (PurchaseOrderGoods ph : listOrder) {
                    if (ph.getId() == order.getId()){
                        ph.setAmount(ph.getAmount()+order.getAmount());
                        i++;
                        tableViewOrder.refresh();
                        break;
                    }


                }
                if(i==0){
                    this.listOrder.add(order);
                    observableListOrder = FXCollections.observableArrayList(listOrder);
                    tableViewOrder.setItems(observableListOrder);
                }
            }


        }

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
        listOrder = new ArrayList<PurchaseOrderGoods>();
        observableListOrder = FXCollections.observableArrayList(listOrder);
        tableViewOrder.setItems(observableListOrder);
    }

    @FXML
    public void saveOrder(){
        if(!listOrder.isEmpty() && !"".equals(supComboBox.getValue())) {
            ArrayList<PurchaseOrderGoods> purchaseOrderGoodsArrayList = (ArrayList<PurchaseOrderGoods>) this.listOrder;
            Supplier supplier = dataManager.getSupplier(supComboBox.getValue());
            if (supplier != null) {
                PurchaseOrder purchaseOrder = new PurchaseOrder(0, "waiting", supplier, purchaseOrderGoodsArrayList);
                dataManager.insertPurchaseOrder(purchaseOrder);
                rePoIdTable();
                reTableImp();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "saved");
                alert.showAndWait();
                listOrder.clear();
                observableListOrder = FXCollections.observableArrayList(listOrder);
                tableViewOrder.setItems(observableListOrder);
            }
            // TODO could show error msg
        }
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    public void calStatusRequisition(){
        System.out.println("ok");
        for(Requisition r : dataManager.getRequisitions()){
            int temp = 0;
            if(r.getStatus().equals("no")){
                for (RequisitionGoods rg : r.getRequisitionGoodsArrayList()){
                    for(Goods g : dataManager.getGoodses()){
                        if(rg.getId() == g.getId()){
                            if(g.getQuantity()>=rg.getAmount()){
                                temp++;
                            }
                        }
                    }
                }
            }
            if(temp == r.getRequisitionGoodsArrayList().size()){
                dataManager.updateStatusRequisition(r);
            }
        }
        reTableRe();
    }

    @FXML
    public void refreshCheckReq() {
        reTableRe();
        reTableImp();
        reTableGoods();
    }

    @FXML
    public void newSupplierAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Warehouse/NewSupplierPage.fxml"));

        try {
            stage.setScene(new Scene((Parent) loader.load()));
            NewSupplierPageController controller = loader.getController();
            controller.setDataManager(dataManager);
            stage.setTitle("New Supplier");
            stage.showAndWait();

            initComboBox();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    /* did not use */
//    public void showSup(){
//        PurchaseOrder sup = (PurchaseOrder) poTableView.getSelectionModel().getSelectedItem();
//
//        observableListSup = FXCollections.observableArrayList(supList);
//        tableViewOrder.setItems(observableListSup);
//    }
}
