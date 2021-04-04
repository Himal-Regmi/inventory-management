package com.ismt.inventorymanagement.dto;

import com.ismt.inventorymanagement.entity.Product;
import com.ismt.inventorymanagement.entity.Purchase;
import com.ismt.inventorymanagement.entity.PurchaseDetail;

public class PurchaseDetailDto {

    private int id;
    private int purchaseId;
    private int productId;
    private int quantity;
    private double rate;

    public PurchaseDetailDto() {
    }

    public PurchaseDetailDto(int id, int purchaseId, int productId, int quantity, double rate) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.quantity = quantity;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public PurchaseDetail changeToPurchaseDetail(int id, Purchase purchase, Product product, int quantity, double rate) {
        PurchaseDetail purchaseDetail= new PurchaseDetail();
        if(id!=0){
            purchaseDetail.setId(id);
        }
        if(purchase!=null){
            purchaseDetail.setPurchase(purchase);
        }
        if(product!=null){
            purchaseDetail.setProduct(product);
        }
        if(quantity!=0){
            purchaseDetail.setQuantity(quantity);
        }
        if(rate!=0){
            purchaseDetail.setRate(rate);
        }
        return purchaseDetail;
    }
}
