package Warehouse.controllers;

import Sale.controllers.DataManager;
import Warehouse.models.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewSupplierPageController {

    @FXML
    private TextField nameTextField, telTextField;
    @FXML
    private TextArea addrTextField;
    @FXML
    private Button cancelBtn;

    private DataManager dataManager;

    public void saveAction(ActionEvent actionEvent) {
        if (!"".equals(nameTextField.getText()) && !"".equals(telTextField.getText()) && !"".equals(addrTextField.getText())) {
            String name = nameTextField.getText();
            String addr = addrTextField.getText();
            String tel = telTextField.getText();
            Supplier supplier = new Supplier(0, name, addr, tel);

            dataManager.addNewSupplier(supplier);
            cancelBtn.getScene().getWindow().hide();
        }
    }

    public void cancelAction(ActionEvent actionEvent) {
        cancelBtn.getScene().getWindow().hide();
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
