package ru.geekbrains.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.geekbrains.persist.BrandRepository;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private SimpMessagingTemplate template;

    @BeforeEach
    public void before() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }
    @AfterEach
    public void after() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }


    @Test
    public void testProductDetails() throws Exception {
        Category category = categoryRepository.save(new Category("Category"));
        Brand brand = brandRepository.save(new Brand("Brand"));
        Product product = productRepository.save(new Product(null, "Product",  new BigDecimal(1234), "Description",category, brand));

        mvc.perform(MockMvcRequestBuilders
                        .get("/v1/product/all")
                        .param("categoryId", category.getId().toString())
                        .param("page", "1")
                        .param("size", "5")
                        .param("sortField", "id")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                // https://github.com/json-path/JsonPath
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].name", is(product.getName())));
    }
    @Test
    public void fakeTest() {
        Category category = categoryRepository.save(new Category("Category"));
        Brand brand = brandRepository.save(new Brand("Brand"));
        Product product = productRepository.save(new Product(null, "Product",  new BigDecimal(1234),"Description", category, brand));
    }
}
