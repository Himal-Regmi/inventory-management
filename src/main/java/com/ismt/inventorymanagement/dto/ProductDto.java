package com.ismt.inventorymanagement.dto;

import com.ismt.inventorymanagement.entity.Category;
import com.ismt.inventorymanagement.entity.Product;
import com.ismt.inventorymanagement.entity.Unit;
import javax.validation.constraints.NotBlank;

public class ProductDto {
    private int id;
    @NotBlank(message = "name cannot be empty")
    private String name;
    private int categoryId;
    private Double rate;
    private int unitId;
    private int quantity;

    public ProductDto() {
    }

    public ProductDto(int id, String name, int categoryId, Double rate, int unitId, int quantity) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.rate = rate;
        this.unitId = unitId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product changeToProduct(int id, String name, Category category,double rate, Unit unit, int quantity){
        Product product= new Product();
        if(id!=0){
            product.setId(id);
        }
        if(name!=null){
            product.setName(name);
        }
        if(category!=null){
            product.setCategory(category);
        }if(rate!=0){
            product.setRate(rate);
        }if(unit!=null){
            product.setUnit(unit);
        }if(quantity!=0){
            product.setQuantity(quantity);
        }
        return product;
    }

}
