package dev.sangco.layoutproduct.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Layout {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "layout_id")
    private String id;

    private String name;

    private int price;

    @OneToMany(mappedBy = "layout")
    List<Product> products = new ArrayList<>();

    @Builder
    public Layout(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
