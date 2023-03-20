package lk.ijse.dep10.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;

public class Item implements Serializable {
    private int code;
    private String description;
    private BigDecimal buyingPrice;
    private BigDecimal sellingPrice;
    private int stock;
    private Blob preview;

    public Item() {
    }

    public Item(int code, String description, BigDecimal buyingPrice, BigDecimal sellingPrice, int stock, Blob preview) {
        this.code = code;
        this.description = description;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.stock = stock;
        this.preview = preview;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Blob getPreview() {
        return preview;
    }

    public void setPreview(Blob preview) {
        this.preview = preview;
    }

    public BigDecimal getProfit(){
        return sellingPrice.subtract(buyingPrice).multiply(new BigDecimal(stock));
    }
}
