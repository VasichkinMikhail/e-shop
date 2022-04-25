package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.service.dto.LineItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    private CartService cartService;
    @BeforeEach
    public void init(){
        cartService = new CartServiceImpl();
    }
    @Test
    public void testIfNewCartIsEmpty(){
        assertNotNull(cartService.getLineItems());
        assertTrue(cartService.getLineItems().isEmpty());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }
    @Test
    public void testAddProduct() {
        ProductDto expectedProduct = new ProductDto();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(123));
        expectedProduct.setName("Product name");

        cartService.addProductQty(expectedProduct, "color", "material", 2);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());

        LineItem lineItem = lineItems.get(0);
        assertEquals("color", lineItem.getColor());
        assertEquals("material", lineItem.getMaterial());
        assertEquals(2, lineItem.getQty());

        assertEquals(expectedProduct.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductDto());
        assertEquals(expectedProduct.getName(), lineItem.getProductDto().getName());
    }

//    @Disabled
//    @Test
//    public void removeProductQtyTest() {
//        ProductDto expectedProduct = new ProductDto();
//        expectedProduct.setId(1L);
//        expectedProduct.setPrice(new BigDecimal(123));
//        expectedProduct.setName("Product name");
//
//        cartService.addProductQty(expectedProduct, "color", "material", 2);
//
//        List<LineItem> lineItems = cartService.getLineItems();
//        assertNotNull(lineItems);
//        assertEquals(1, lineItems.size());
//
//        cartService.removeProductQty(expectedProduct,"color", "material", 2);
//
//        assertTrue(cartService.getLineItems().isEmpty());
//        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
//    }
}
