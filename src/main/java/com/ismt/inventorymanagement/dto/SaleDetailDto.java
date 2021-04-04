package com.ismt.inventorymanagement.dto;

import com.ismt.inventorymanagement.entity.Product;
import com.ismt.inventorymanagement.entity.Sale;
import com.ismt.inventorymanagement.entity.SaleDetail;

public class SaleDetailDto {

    private int id;
    private int saleId;
    private int productId;
    private int quantity;
    private double rate;

    public SaleDetailDto() {
    }

    public SaleDetailDto(int id, int saleId, int productId, int quantity, double rate) {
        this.id = id;
        this.saleId = saleId;
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

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
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

    public SaleDetail changeToSaleDetail(int id, Sale sale, Product product, int quantity, double rate) {
        SaleDetail saleDetail= new SaleDetail();
        if(id!=0){
            saleDetail.setId(id);
        }
        if(sale!=null){
            saleDetail.setSale(sale);
        }
        if(product!=null){
            saleDetail.setProduct(product);
        }
        if(quantity!=0){
            saleDetail.setQuantity(quantity);
        }
        if(rate!=0){
            saleDetail.setRate(rate);
        }
        return saleDetail;
    }
}
