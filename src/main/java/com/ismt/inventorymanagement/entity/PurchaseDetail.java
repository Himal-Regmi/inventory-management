package com.ismt.inventorymanagement.entity;

import com.ismt.inventorymanagement.dto.PurchaseDetailDto;
import javax.persistence.*;

@Entity
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
    @ManyToOne(optional = false,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private double rate;

    public PurchaseDetail() {
    }

    public PurchaseDetail(int id, Purchase purchase, Product product, int quantity, double rate) {
        this.id = id;
        this.purchase = purchase;
        this.product = product;
        this.quantity = quantity;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public PurchaseDetailDto changeToPurchaseDetailDto() {
        PurchaseDetailDto purchaseDetailDto=new PurchaseDetailDto();
        if(id!=0){
            purchaseDetailDto.setId(id);
        }
        if(purchase!=null){
            purchaseDetailDto.setPurchaseId(purchase.getId());
        }
        if(product!=null){
            purchaseDetailDto.setProductId(product.getId());
        }
        if(id!=0){
            purchaseDetailDto.setQuantity(quantity);
        }
        if(id!=0){
            purchaseDetailDto.setRate(rate);
        }
        return purchaseDetailDto;
    }
}
