package com.ismt.inventorymanagement.entity;

import com.ismt.inventorymanagement.dto.SaleDto;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    @ManyToOne(optional = false,cascade =
            {CascadeType.MERGE,CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private double total;
    private double discount;

    public Sale(int id, Date date, Customer customer, double total, double discount) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.total = total;
        this.discount = discount;
    }

    public Sale() {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
    public SaleDto createSaleDto(){
        SaleDto saleDto=new SaleDto();
        if(this.id!=0){
            saleDto.setId(this.id);
        }
        if(this.date!=null){
            saleDto.setDate(this.date);
        }
        if(this.customer !=null){
            saleDto.setCustomerId(this.customer.getId());
        }if(this.total!=0){
            saleDto.setTotal(this.total);
        }if(this.discount!=0){
            saleDto.setDiscount(this.discount);
        }
        return saleDto;
    }
}
