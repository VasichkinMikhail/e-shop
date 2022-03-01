package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.controller.dto.BrandDto;
import ru.geekbrains.service.BrandService;


import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/brand")
public class BrandController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String listPage(@RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField, Model model) {
        model.addAttribute("brands", brandService.findAll(
                page.orElse(1) - 1,
                size.orElse(5),
                sortField.filter(fld -> !fld.isBlank()).orElse("id")));
        return "brands";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activePage", "Brand");
    }

    @GetMapping("/new")
    public String newBrandForm(Model model) {
        logger.info("New brand page requested");

        model.addAttribute("brand", new BrandDto());
        return "brand_form";
    }

    @GetMapping("/{id}")
    public String editBrand(@PathVariable("id") Long id, Model model) {
        logger.info("Edit brand page requested");

        model.addAttribute("brand", brandService.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found")));
        return "brand_form";
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("brand") BrandDto brandDto, BindingResult result) {
        logger.info("Saving brand");

        if (result.hasErrors()) {
            return "brand_form";
        }
        brandService.save(brandDto);
        return "redirect:/brand";
    }

    @DeleteMapping("/{id}")
    public String deleteBrand(@PathVariable("id") Long id) {
        logger.info("Deleting brand with id {}", id);

        brandService.deleteById(id);
        return "redirect:/brand";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
