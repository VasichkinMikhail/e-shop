package ru.geekbrains.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private CategoryDto category;

    private List<Long> pictures;

    private BrandDto brand;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, BigDecimal price,
                      CategoryDto category, BrandDto brand, List<Long> pictures) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.pictures = pictures;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<Long> getPictures() {
        return pictures;
    }

    public void setPictures(List<Long> pictures) {
        this.pictures = pictures;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }
}
