package Sale.controllers;

import Sale.models.RequisitionGoods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddAmountController {

    @FXML
    private Label idLabel, typeLabel, brandLabel, nameLabel;
    @FXML
    private TextField amountTextField;
    @FXML
    private Button addBtn;

    private RequisitionGoods requisitionGoods;

    public void addGoods(ActionEvent actionEvent) {
        if ("".equals(this.amountTextField.getText()) || this.amountTextField.getText().length()>4 || this.amountTextField.getText().substring(0,1).equals("-") || this.amountTextField.getText().equals("0"))
            return;
        try {
            int i = Integer.parseInt(this.amountTextField.getText());
            requisitionGoods.setAmount(Integer.parseInt(amountTextField.getText()));
            addBtn.getScene().getWindow().hide();
        }
        catch(NumberFormatException nfe)
        {
            return;
        }
    }

    public Label getIdLabel() {
        return idLabel;
    }

    public Label getTypeLabel() {
        return typeLabel;
    }

    public Label getBrandLabel() {
        return brandLabel;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public RequisitionGoods getRequisitionGoods() {
        return requisitionGoods;
    }

    public void setRequisitionGoods(RequisitionGoods requisitionGoods) {
        this.requisitionGoods = requisitionGoods;
    }
}
