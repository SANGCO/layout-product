package dev.sangco.layoutproduct.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sangco.layoutproduct.domain.Layout;
import dev.sangco.layoutproduct.domain.Product;
import dev.sangco.layoutproduct.repository.LayoutRepository;
import dev.sangco.layoutproduct.repository.ProductRepository;
import dev.sangco.layoutproduct.web.dto.ProductRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LayoutRepository layoutRepository;

    @Autowired
    private ProductRepository productRepository;

    private ProductRequestDto requestDto;
    private Product product;

    @BeforeEach
    public void setUp() {
        List<Layout> layoutList = layoutRepository.findAll();
        Layout layout = layoutList.get(0);

        List<Product> productList = productRepository.findAll();
        product = productList.get(0);

        requestDto = ProductRequestDto.builder()
                .name("테스트 프로덕트")
                .price(1000)
                .layoutId(layout.getId())
                .build();
    }

    @Test
    @DisplayName("Product save 테스트")
    public void saveProductTest() throws Exception {
        mockMvc.perform(post("/products")
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(LOCATION))
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("data.name").value("테스트 프로덕트"));
    }

    @Test
    @DisplayName("Product save fail 테스트")
    public void saveProductFailTest() throws Exception {
        requestDto.setName("");

        mockMvc.perform(post("/products")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("fieldErrors").isArray());
    }

    @Test
    @DisplayName("Product update 테스트")
    public void updateProductTest() throws Exception {
        requestDto.setName("테스트 업데이트 프로덕트");
        requestDto.setPrice(9999);

        mockMvc.perform(put("/products/" + product.getId())
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Product update fail 테스트")
    public void updateProductFailTest() throws Exception {
        requestDto.setName("");

        mockMvc.perform(put("/products/" + product.getId())
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("fieldErrors[0].field").value("name"));
    }

    @Test
    @DisplayName("Product delete 테스트")
    public void deleteProductTest() throws Exception {
        mockMvc.perform(delete("/products/" + product.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Product delete fail 테스트")
    public void deleteProductFailTest() throws Exception {
        mockMvc.perform(delete("/products/" + product.getId()))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("code").value("IllegalArgumentException"));
    }

    @Test
    @DisplayName("Product findAll 조회 테스트")
    public void findAllProductTest() throws Exception {
        mockMvc.perform(get("/products")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("data").isArray());
    }

}