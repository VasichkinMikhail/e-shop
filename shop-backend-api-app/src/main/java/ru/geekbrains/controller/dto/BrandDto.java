package ru.geekbrains.controller.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class BrandDto implements Serializable {
    private Long id;

    private String name;

    public BrandDto() {
    }

    public BrandDto(Long id) {
        this.id = id;
    }

    public BrandDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
