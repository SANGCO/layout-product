package dev.sangco.layoutproduct.domain;

import dev.sangco.layoutproduct.web.dto.ProductRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "product_id")
    private String id;

    private String name;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "layout_id")
    private Layout layout;

    @Builder
    public Product(String id, String name, int price, Layout layout) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.layout = layout;
    }

    public void update(String name, int price, Layout layout) {
        this.name = name;
        this.price = price;
        this.layout = layout;
    }

}
