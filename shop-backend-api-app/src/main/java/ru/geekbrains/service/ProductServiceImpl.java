package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.dto.CategoryDto;
import ru.geekbrains.controller.dto.BrandDto;
import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductSpecification;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findAll(Optional<Long> brandId, Optional<Long> categoryId, Optional<String> nameFilter,
                                    Integer page, Integer size, String sort) {

        Specification<Product> spec = Specification.where(null);
        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
            spec = spec.and(ProductSpecification.nameLike(nameFilter.get()));
        }

        if (categoryId.isPresent() && categoryId.get() != -1) {
            spec = spec.and(ProductSpecification.byCategory(categoryId.get()));
        }

        if (brandId.isPresent() && brandId.get() != -1) {
            spec = spec.and(ProductSpecification.byBrand(brandId.get()));
        }

        return productRepository.findAll(spec,
                        PageRequest.of(page, size, Sort.by(sort)))
                .map(ProductServiceImpl::convertToDto);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(ProductServiceImpl::convertToDto);
    }

    private static ProductDto convertToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                new CategoryDto(product.getCategory().getId(),
                        product.getCategory().getName()),
                new BrandDto(product.getBrand().getId(),
                        product.getBrand().getName()),
                product.getPictures()
                        .stream()
                        .map(Picture::getId)
                        .collect(Collectors.toList())


        );
    }
}
