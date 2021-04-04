package com.ismt.inventorymanagement.entity;

import com.ismt.inventorymanagement.dto.PurchaseDto;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    @ManyToOne(optional = false,cascade =
            {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    private double total;
    private double discount;

    public Purchase(int id, Date date, Supplier supplier, double total, double discount) {
        this.id = id;
        this.date = date;
        this.supplier = supplier;
        this.total = total;
        this.discount = discount;
    }

    public Purchase() {
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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
    public PurchaseDto createPurchaseDto(){
        PurchaseDto purchaseDto=new PurchaseDto();
        if(this.id!=0){
            purchaseDto.setId(this.id);
        }
        if(this.date!=null){
            purchaseDto.setDate(this.date);
        }
        if(this.supplier!=null){
            purchaseDto.setSupplierId(this.supplier.getId());
        }if(this.total!=0){
            purchaseDto.setTotal(this.total);
        }if(this.discount!=0){
            purchaseDto.setDiscount(this.discount);
        }
        return purchaseDto;
    }
}
