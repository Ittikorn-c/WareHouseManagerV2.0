package Sale.controllers;

import Sale.dataSources.DBConnector;

import Sale.models.Goods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddNewGoodsController {

    @FXML
    private ComboBox<String> typeComboBox, brandComboBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button addBtn;

    private DataManager dataManager;

    @FXML
    public void initialize() {
//        typeComboBox.getItems().clear();
//        brandComboBox.getItems().clear();
    }

    @FXML
    public void addNewGoods(ActionEvent actionEvent) {
        if (typeComboBox.getValue() != null && brandComboBox.getValue() != null) {
            Goods g = new Goods(0, typeComboBox.getValue(), brandComboBox.getValue(), nameTextField.getText(), 0);
            dataManager.insertGoods(g);
            dataManager.getGoodses().add(g);

            addBtn.getScene().getWindow().hide();
        }
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;

        typeComboBox.getItems().setAll(dataManager.getAllTypes());
        brandComboBox.getItems().setAll(dataManager.getAllBrands());
    }
}
