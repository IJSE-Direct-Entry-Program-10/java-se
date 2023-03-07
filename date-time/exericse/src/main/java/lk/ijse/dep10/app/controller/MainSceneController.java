package lk.ijse.dep10.app.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class MainSceneController {

    public ComboBox<String> cmbType;
    public DatePicker dtpIn;
    public DatePicker dtpOut;
    public Label lblHint;
    public Label lblRate;
    public Label lblTotal;

    public void initialize(){
        ObservableList<String> typeList = cmbType.getItems();
        typeList.add("Economy");
        typeList.add("Premium Economy");
        typeList.add("Business Executive");
        typeList.add("First Class");
    }

    public void cmbTypeOnAction(ActionEvent event) {
        String selectedType = cmbType.getSelectionModel().getSelectedItem();
        switch (selectedType) {
            case "Economy":
                lblRate.setText("Rate: $5");
                break;
            case "Premium Economy":
                lblRate.setText("Rate $7");
                break;
            case "Business Executive":
                lblRate.setText("Rate: $10");
                break;
            case "First Class":
                lblRate.setText("Rate: $15");
                break;
            default:
                lblRate.setText("Select a type to display the rate");
        }
        calculateTotal();
    }

    public void dtpInOnAction(ActionEvent event) {
        calculateTotal();
    }


    public void dtpOutOnAction(ActionEvent event) {
        calculateTotal();
    }

    private void calculateTotal(){
        lblTotal.setText("Total: -");
        boolean isValid = true;
        long days = 0;

        int selectedIndex = cmbType.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) isValid = false;

        if (dtpIn.getValue() == null || dtpOut.getValue() == null){
            lblHint.setText("Select In and Out Dates");
            isValid = false;
        }else if(dtpIn.getValue().isAfter(dtpOut.getValue())){
            lblHint.setText("In and Out Dates are Invalid");
            isValid = false;
        }else if (dtpIn.getValue().isEqual(dtpOut.getValue())){
            lblHint.setText("In and Out Dates are same");
            isValid = false;
        }else{
            LocalDate inDate = dtpIn.getValue();
            LocalDate outDate = dtpOut.getValue();

            days = Duration.between(inDate.atStartOfDay(), outDate.atStartOfDay()).toDays();
            lblHint.setText("Num of days: " + days);
        }

        if (!isValid) return;
        int rate = new int[]{5, 7, 10, 15}[selectedIndex];
        BigDecimal total = new BigDecimal(rate).multiply(new BigDecimal(days));
        lblTotal.setText(String.format("Total: %.2f", total));
    }

}
