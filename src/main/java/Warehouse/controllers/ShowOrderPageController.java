package Warehouse.controllers;

import Sale.controllers.DataManager;
import Sale.models.Requisition;
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
    private TableView orderIDTable;
    @FXML
    private TableColumn<Requisition,Integer> orderIDCol;

    private List<Requisition> requisitionList = new ArrayList<Requisition>();
    private ObservableList<Requisition> observableListOrder;



    @FXML
    private void initialize(){


        this.orderIDCol.setCellValueFactory(new PropertyValueFactory<Requisition, Integer>("id"));

        observableListOrder = FXCollections.observableArrayList(requisitionList);
        orderIDTable.setItems(observableListOrder);

    }
    public void loadReq(){
        this.requisitionList = dataManager.getRequisitions();
    }

    public void printAllAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/WareHouse/SampleViewPage.fxml"));

        try {
            stage.setScene(new Scene((Parent) loader.load()));
            SampleView controller = loader.getController();
            /*
            print by selected
             */
            if((Requisition)orderIDTable.getSelectionModel().getSelectedItem()!= null){
                this.requisitionList.add((Requisition)orderIDTable.getSelectionModel().getSelectedItem());
            }
            controller.setRequisitionList(this.requisitionList);
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
