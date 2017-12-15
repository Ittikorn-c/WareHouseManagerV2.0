package Warehouse.controllers;

import Sale.models.Goods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddAmountOnPurchasePageController {
    Goods goods;
    @FXML
    Label idLabel;
    @FXML
    Label typeLabel;
    @FXML
    Label brandLabel;
    @FXML
    Label nameLabel;
    @FXML
    Button addBtn;

    @FXML
    TextField amountTextField;

    @FXML
    public void addOrder(){

        this.goods.setQuantity(Integer.parseInt(this.amountTextField.getText()));
        addBtn .getScene().getWindow().hide();
    }
}
