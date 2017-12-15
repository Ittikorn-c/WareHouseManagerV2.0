package Warehouse.controllers;

import Sale.models.Goods;
import Sale.models.RequisitionGoods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddAmountOnPurchasePageController {

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

    private Goods goods;
    @FXML
    public void addOrder(){
        if ("".equals(this.amountTextField.getText()))
            return;

        if (this.goods instanceof RequisitionGoods)
            ((RequisitionGoods) this.goods).setAmount(Integer.parseInt(this.amountTextField.getText()));
        else
            this.goods.setQuantity(this.goods.getQuantity() + Integer.parseInt(this.amountTextField.getText()));
        addBtn.getScene().getWindow().hide();
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
