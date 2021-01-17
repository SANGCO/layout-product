package dev.sangco.layoutproduct.service;

import dev.sangco.layoutproduct.repository.LayoutRepository;
import dev.sangco.layoutproduct.repository.ProductRepository;
import dev.sangco.layoutproduct.web.dto.ProductRequestDto;
import dev.sangco.layoutproduct.web.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final LayoutRepository layoutRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto save(ProductRequestDto requestDto) {

        return new ProductResponseDto();
    }

    @Transactional
    public ProductResponseDto update(Long id, ProductRequestDto requestDto) {

        return new ProductResponseDto();
    }

    @Transactional
    public void delete(Long id) {

    }

    public ProductResponseDto findById(Long id) {

        return new ProductResponseDto();
    }

    public List<ProductResponseDto> findAll() {

        return new ArrayList<>();
    }

}
