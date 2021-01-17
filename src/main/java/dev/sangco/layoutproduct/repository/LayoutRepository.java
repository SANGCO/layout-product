package dev.sangco.layoutproduct.repository;

import dev.sangco.layoutproduct.domain.Layout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LayoutRepository extends JpaRepository<Layout, String> {
}
