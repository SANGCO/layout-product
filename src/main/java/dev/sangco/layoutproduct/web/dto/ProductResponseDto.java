package dev.sangco.layoutproduct.web.dto;

import dev.sangco.layoutproduct.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductResponseDto {

    private String id;
    private String name;
    private int price;
    private String layoutId;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.layoutId = product.getLayout().getId();
    }
}
