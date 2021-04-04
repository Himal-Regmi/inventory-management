package com.ismt.inventorymanagement.dto;

import com.ismt.inventorymanagement.entity.Customer;
import com.ismt.inventorymanagement.entity.Sale;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class SaleDto {
    private int id;

    private Date date;
    @NotNull
    private int customerId;
    private double total;
    private double discount;

    public SaleDto(int id, Date date, int customerId, double total, double discount) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.total = total;
        this.discount = discount;
    }

    public SaleDto() {
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
    public Sale changeToSale(int id, Date date, Customer customer, double total, double discount){
        Sale sale= new Sale();
        if(id!=0){
            sale.setId(id);
        }
        if(date!=null){
            sale.setDate(date);
        }
        if(customer!=null){
            sale.setCustomer(customer);
        }if(total!=0){
            sale.setTotal(total);
        }if(discount!=0){
            sale.setDiscount(discount);
        }
        return sale;
    }
}
