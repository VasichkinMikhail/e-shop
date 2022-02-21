package ru.geekbrains.persist.model;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "products")
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @Column(nullable = false)
        private String name;


        @Column
        private String description;


        @Column
        private BigDecimal price;

        @ManyToOne
        private Category category;

        public Product() {
        }

        public Product(Long id, String name, BigDecimal price, String description, Category category) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.description = description;
            this.category = category;
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
}


