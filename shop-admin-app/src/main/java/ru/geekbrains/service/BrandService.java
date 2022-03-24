package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.dto.BrandDto;
import ru.geekbrains.controller.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandDto> findAll();

    Page<BrandDto> findAll(Integer page, Integer size, String sortField);

    Optional<BrandDto> findById(Long id);

    void save(BrandDto brandDto);

    void deleteById(Long id);
}
