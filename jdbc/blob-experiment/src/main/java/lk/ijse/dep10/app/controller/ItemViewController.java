package lk.ijse.dep10.app.controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import lk.ijse.dep10.app.db.DBConnection;
import lk.ijse.dep10.app.model.Item;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.*;

public class ItemViewController {

    public Button btnDeleteItem;
    public Button btnImage;
    public Button btnNewItem;
    public Button btnSaveItem;
    public ImageView imgPreview;
    public AnchorPane root;
    public StackPane sckImage;
    public TableView<Item> tblItems;
    public TextField txtBuyingPrice;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtQty;
    public TextField txtSellingPrice;
    public AnchorPane pnImage;

    public void initialize() {
        Platform.runLater(btnNewItem::fire);
        sckImage.setOnMouseEntered(mouseEvent -> pnImage.toFront());
        sckImage.setOnMouseExited(mouseEvent -> pnImage.toBack());
        sckImage.focusedProperty().addListener((ov, prev, current) -> {
            if (current) {
                pnImage.toFront();
            } else {
                pnImage.toBack();
            }
        });

        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblItems.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("profit"));

        loadAllItems();

        btnDeleteItem.setDisable(true);
        tblItems.getSelectionModel().selectedItemProperty().addListener((ov, prev, current) -> {
            btnDeleteItem.setDisable(current == null);
            if (current == null) return;

            txtCode.setText(current.getCode() + "");
            txtDescription.setText(current.getDescription());
            txtBuyingPrice.setText(current.getBuyingPrice().toString());
            txtSellingPrice.setText(current.getSellingPrice().toString());
            txtQty.setText(current.getStock() + "");

            if (current.getPreview() != null) {
                try {
                    /* Blob -> Java FX Image */
                    InputStream is = current.getPreview().getBinaryStream();
                    Image itemImage = new Image(is);
                    imgPreview.setImage(itemImage);
                    btnImage.setText("CLEAR");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                imgPreview.setImage(null);
                btnImage.setText("BROWSE");
            }
        });
    }

    private void loadAllItems() {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Item");
            PreparedStatement stmPicture = connection.
                    prepareStatement("SELECT * FROM Item_Picture WHERE item_code = ?");

            while (rst.next()) {
                int code = rst.getInt("code");
                String description = rst.getString("description");
                BigDecimal buyingPrice = rst.getBigDecimal("buying_price");
                BigDecimal sellingPrice = rst.getBigDecimal("selling_price");
                int stock = rst.getInt("stock");
                Item item = new Item(code, description, buyingPrice, sellingPrice,
                        stock, null);

                stmPicture.setInt(1, code);
                ResultSet rstPicture = stmPicture.executeQuery();
                if (rstPicture.next()) {
                    item.setPreview(rstPicture.getBlob("preview"));
                }
                tblItems.getItems().add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load items").showAndWait();
            System.exit(1);
        }
    }

    public void btnDeleteItemOnAction(ActionEvent event) {

    }

    public void btnImageOnAction(ActionEvent event) throws MalformedURLException {
        if (btnImage.getText().equals("BROWSE")) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the item picture");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",
                    "*.bmp", "*.jpeg", "*.jpg", "*.gif", "*.png"));
            File file = fileChooser.showOpenDialog(btnImage.getScene().getWindow());
            if (file != null) {
                Image image = new Image(file.toURI().toURL().toString());
                imgPreview.setImage(image);
                btnImage.setText("CLEAR");
            }
        } else {
            btnImage.setText("BROWSE");
            imgPreview.setImage(null);
        }
    }


    public void btnSaveItemOnAction(ActionEvent event) {
        if (!isDataValid()) return;

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement stmItem = connection.prepareStatement
                    ("INSERT INTO Item (description, buying_price, selling_price, stock) VALUES (?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmItemPicture = connection.prepareStatement
                    ("INSERT INTO Item_Picture (item_code, preview) VALUES (?, ?)");

            connection.setAutoCommit(false);

            stmItem.setString(1, txtDescription.getText());
            stmItem.setBigDecimal(2, new BigDecimal(txtBuyingPrice.getText()));
            stmItem.setBigDecimal(3, new BigDecimal(txtSellingPrice.getText()));
            stmItem.setInt(4, Integer.parseInt(txtQty.getText()));
            stmItem.executeUpdate();

            ResultSet generatedKeys = stmItem.getGeneratedKeys();
            generatedKeys.next();
            int code = generatedKeys.getInt(1);

            Item newItem = new Item(code, txtDescription.getText(),
                    new BigDecimal(txtBuyingPrice.getText()), new BigDecimal(txtSellingPrice.getText()),
                    Integer.parseInt(txtQty.getText()), null);

            Image image = imgPreview.getImage();
            if (image != null){
                /* JavaFX Image -> Blob */
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                byte[] bytes = baos.toByteArray();
                Blob itemImage = new SerialBlob(bytes);

                newItem.setPreview(itemImage);

                stmItemPicture.setInt(1, code);
                stmItemPicture.setBlob(2, itemImage);
                stmItemPicture.executeUpdate();
            }

            connection.commit();
            tblItems.getItems().add(newItem);
            btnNewItem.fire();
        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new Alert(Alert.AlertType.ERROR, "Failed to save the item").show();
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isDataValid() {
        boolean dataValid = true;

        String description = txtDescription.getText().strip();
        String buyingPrice = txtBuyingPrice.getText().strip();
        String sellingPrice = txtSellingPrice.getText().strip();
        String stock = txtQty.getText().strip();

        if (!stock.matches("^\\d+$") || Integer.parseInt(stock) <= 0) {
            txtQty.requestFocus();
            txtQty.selectAll();
            dataValid = false;
        }

        if (!sellingPrice.matches("^\\d+([.]\\d{0,2})?$") ||
                (new BigDecimal(sellingPrice).compareTo(BigDecimal.ZERO) == 0)) {
            txtSellingPrice.requestFocus();
            txtSellingPrice.selectAll();
            dataValid = false;
        }

        if (!buyingPrice.matches("\\d+([.]\\d{0,2})?") ||
                (new BigDecimal(buyingPrice).compareTo(BigDecimal.ZERO) == 0)) {
            txtBuyingPrice.requestFocus();
            txtBuyingPrice.selectAll();
            dataValid = false;
        }

        if (!description.matches("[A-Za-z0-9 ]+")) {
            txtDescription.requestFocus();
            txtDescription.selectAll();
            dataValid = false;
        }

        return dataValid;
    }

    public void btnNewItemOnAction(ActionEvent actionEvent) {
        txtCode.setText("Generated Code");
        txtDescription.clear();
        txtBuyingPrice.clear();
        txtSellingPrice.clear();
        txtQty.clear();
        tblItems.getSelectionModel().clearSelection();
        imgPreview.setImage(null);
        btnImage.setText("BROWSE");

        txtDescription.requestFocus();
    }
}
