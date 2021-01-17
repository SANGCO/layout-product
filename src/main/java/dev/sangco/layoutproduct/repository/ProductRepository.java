package dev.sangco.layoutproduct.repository;

import dev.sangco.layoutproduct.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
