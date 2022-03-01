package ru.geekbrains.persist.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 65560, columnDefinition = "LONGTEXT")
    private String description;

    @Column
    private BigDecimal price;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Brand brand;

    @OneToMany(mappedBy = "product",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<>();


        public Product() {
        }

        public Product(Long id, String name, BigDecimal price, String description, Category category, Brand brand) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.description = description;
            this.category = category;
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

        public Category getCategory() {
        return category;
         }

         public void setCategory(Category category) {
        this.category = category;
         }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


