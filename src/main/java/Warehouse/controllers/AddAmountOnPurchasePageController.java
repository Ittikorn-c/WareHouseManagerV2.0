package Warehouse.controllers;

import Sale.models.Goods;
import Sale.models.RequisitionGoods;
import Warehouse.models.PurchaseOrderGoods;
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
        if ("".equals(this.amountTextField.getText()) || this.amountTextField.getText().length()>4 || this.amountTextField.getText().substring(0,1).equals("-") || this.amountTextField.getText().equals("0"))
            return;
        try {
            int i = Integer.parseInt(this.amountTextField.getText());
            if (this.goods instanceof PurchaseOrderGoods)
                ((PurchaseOrderGoods) this.goods).setAmount(Integer.parseInt(this.amountTextField.getText()));
            else
                this.goods.setQuantity(this.goods.getQuantity() + Integer.parseInt(this.amountTextField.getText()));
            addBtn.getScene().getWindow().hide();
        }
        catch(NumberFormatException nfe)
        {
            return;
        }

    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
