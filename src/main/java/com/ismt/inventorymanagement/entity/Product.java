package com.ismt.inventorymanagement.entity;

import com.ismt.inventorymanagement.dto.ProductDto;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "name cannot be empty")
    private String name;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;
    private Double rate;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "unit_id")
    private Unit unit;
    private int quantity;

    public Product() {
    }

    public Product(int id, String name, Category category, Double rate, Unit unit, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rate = rate;
        this.unit = unit;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public ProductDto createProductDto(){
        ProductDto productDto= new ProductDto();
        if(this.id!=0){
            productDto.setId(this.id);
        }
        if(this.name!=null){
            productDto.setName(this.name);
        }
        if(this.category!=null){
            productDto.setCategoryId(this.category.getId());
        }if(this.rate!=0){
            productDto.setRate(this.rate);
        }if(this.unit!=null){
            productDto.setUnitId(this.unit.getId());
        }if(this.quantity!=0){
            productDto.setQuantity(this.quantity);
        }
        return productDto;
    }
}
