package com.wipro.model;


public class Inventory {
    
    private int inventoryId;
    private int quantity;
    private int productId;

    public Inventory(int inventoryId, int quantity, int productId) {
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.productId = productId;
    }

    public Inventory( int quantity, int productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public Inventory() {
        super();
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Inventory [inventoryId=" + inventoryId + ", quantity=" + quantity + ", productId= " + productId + "]";
    }

}
