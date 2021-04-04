package com.ismt.inventorymanagement.entity;

import com.ismt.inventorymanagement.dto.SaleDetailDto;
import javax.persistence.*;

@Entity
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "sale_id")
    private Sale sale;
    @ManyToOne(optional = false,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private double rate;

    public SaleDetail() {
    }

    public SaleDetail(int id, Sale sale, Product product, int quantity, double rate) {
        this.id = id;
        this.sale = sale;
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

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
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

    public SaleDetailDto changeToSaleDetailDto() {
        SaleDetailDto saleDetailDto=new SaleDetailDto();
        if(id!=0){
            saleDetailDto.setId(id);
        }
        if(sale !=null){
            saleDetailDto.setSaleId(sale.getId());
        }
        if(product!=null){
            saleDetailDto.setProductId(product.getId());
        }
        if(id!=0){
            saleDetailDto.setQuantity(quantity);
        }
        if(id!=0){
            saleDetailDto.setRate(rate);
        }
        return saleDetailDto;
    }
}
