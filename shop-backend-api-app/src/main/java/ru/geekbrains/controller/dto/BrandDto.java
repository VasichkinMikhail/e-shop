package ru.geekbrains.controller.dto;

public class BrandDto {
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
