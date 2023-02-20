package lk.ijse.dep10.se.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep10.se.model.Item;

import java.math.BigDecimal;

public class MainSceneController {

    public Label lblProfit;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<Item> tblItems;

    @FXML
    private TextField txtBuyingPrice;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSellingPrice;

    @FXML
    void btnNewOnAction(ActionEvent event) {
        txtCode.setText(generateNewItemCode());

        for (TextField txt : new TextField[]{txtDescription, txtBuyingPrice, txtSellingPrice, txtQty}) {
            txt.clear();
        }

        tblItems.getSelectionModel().clearSelection();
        txtDescription.requestFocus();
    }

    private String generateNewItemCode() {
        ObservableList<Item> itemList = tblItems.getItems();
        if (itemList.isEmpty()) return  "I001";

        String lastItemCode = itemList.get(itemList.size() - 1).getCode();  // Eg. I005
        int newItemCode = Integer.parseInt(lastItemCode.substring(1)) + 1;
        return String.format("I%03d", newItemCode);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        Item item = new Item(txtCode.getText(),
                txtDescription.getText(),
                new BigDecimal(txtBuyingPrice.getText()),
                new BigDecimal(txtSellingPrice.getText()),
                Integer.parseInt(txtQty.getText()));
        ObservableList<Item> itemList = tblItems.getItems();
        itemList.add(item);
        calculateEstimatedProfit();
        btnNew.fire();
    }

    private void calculateEstimatedProfit(){
        ObservableList<Item> itemList = tblItems.getItems();
        BigDecimal estimatedTotalProfit = BigDecimal.ZERO;
        for (Item item : itemList) {
            estimatedTotalProfit = estimatedTotalProfit.add(item.getTotalProfit());
        }
        lblProfit.setText("Est. Total Profit: " + estimatedTotalProfit.setScale(2));
    }

    public void initialize(){
        ObservableList<TableColumn<Item, ?>> columnList = tblItems.getColumns();
        columnList.get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        columnList.get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        columnList.get(2).setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        columnList.get(3).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        columnList.get(4).setCellValueFactory(new PropertyValueFactory<>("profit"));
        columnList.get(5).setCellValueFactory(new PropertyValueFactory<>("qty"));
        columnList.get(6).setCellValueFactory(new PropertyValueFactory<>("total"));
        columnList.get(7).setCellValueFactory(new PropertyValueFactory<>("totalProfit"));
    }

}
