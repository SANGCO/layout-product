package dev.sangco.layoutproduct.service;

import dev.sangco.layoutproduct.domain.Layout;
import dev.sangco.layoutproduct.domain.Product;
import dev.sangco.layoutproduct.repository.LayoutRepository;
import dev.sangco.layoutproduct.repository.ProductRepository;
import dev.sangco.layoutproduct.web.dto.ProductRequestDto;
import dev.sangco.layoutproduct.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final LayoutRepository layoutRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto save(ProductRequestDto requestDto) {
        Layout layout = getLayout(requestDto.getLayoutId());
        Product product = Product.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .layout(layout)
                .build();
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDto(savedProduct);
    }

    @Transactional
    public void update(String id, ProductRequestDto requestDto) {
        Layout layout = getLayout(requestDto.getLayoutId());
        Product product = getProduct(id);
        product.update(requestDto.getName(), requestDto.getPrice(), layout);
    }

    @Transactional
    public void delete(String id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    public ProductResponseDto findById(String id) {
        Product product = getProduct(id);
        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponseDto::new).collect(Collectors.toList());
    }

    private Layout getLayout(String id) {
        return layoutRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 Layout이 없습니다. Layout ID = " + id));
    }

    private Product getProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 Product가 없습니다. Layout ID = " + id));
    }

}
