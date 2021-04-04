package com.ismt.inventorymanagement.dto;

import com.ismt.inventorymanagement.entity.Purchase;
import com.ismt.inventorymanagement.entity.Supplier;
import java.sql.Date;

public class PurchaseDto {
    private int id;

    private Date date;
    private int SupplierId;
    private double total;
    private double discount;

    public PurchaseDto(int id, Date date, int SupplierId, double total, double discount) {
        this.id = id;
        this.date = date;
        this.SupplierId = SupplierId;
        this.total = total;
        this.discount = discount;
    }

    public PurchaseDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        this.SupplierId = supplierId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public Purchase changeToPurchase(int id, Date date, Supplier supplier, double total, double discount){
        Purchase purchase= new Purchase();
        if(id!=0){
            purchase.setId(id);
        }
        if(date!=null){
            purchase.setDate(date);
        }
        if(supplier!=null){
            purchase.setSupplier(supplier);
        }if(total!=0){
            purchase.setTotal(total);
        }if(discount!=0){
            purchase.setDiscount(discount);
        }
        return purchase;
    }
}
