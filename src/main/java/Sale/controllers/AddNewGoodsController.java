package Sale.controllers;

import Sale.dataSources.DBConnector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddNewGoodsController {

    @FXML
    private ComboBox typeComboBox, brandComboBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button addBtn;

    private DataManager dataManager;

    public void addNewGoods(ActionEvent actionEvent) {
        DBConnector dbConnector = new DBConnector();
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
