package ru.geekbrains.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.service.ProductService;

import java.util.Optional;

@Tag(name = "Product", description = "Service to get product information")
@RequestMapping("v1/product")
@RestController

public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public Page<ProductDto> findAll(@RequestParam("categoryId") Optional<Long> categoryId,
                                    @RequestParam("brandId") Optional<Long> brandId,
                                    @RequestParam("nameFilter") Optional<String> nameFilter,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    @RequestParam("sort") Optional<String> sort) {
        return productService.findAll(
                categoryId,
                brandId,
                nameFilter,
                page.orElse(1) - 1,
                size.orElse(5),
                sort.filter(fld -> !fld.isBlank()).orElse("id"));
    }
}
