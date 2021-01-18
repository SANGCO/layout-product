package dev.sangco.layoutproduct;

import dev.sangco.layoutproduct.domain.Layout;
import dev.sangco.layoutproduct.domain.Product;
import dev.sangco.layoutproduct.repository.LayoutRepository;
import dev.sangco.layoutproduct.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class LayoutProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(LayoutProductApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(LayoutRepository layoutRepository,
							 ProductRepository productRepository) {
		return (args -> {
			Layout layout1 = Layout.builder()
					.name("테스트1")
					.price(10000)
					.build();
			layoutRepository.save(layout1);

			Layout layout2 = Layout.builder()
					.name("테스트2")
					.price(20000)
					.build();
			layoutRepository.save(layout2);

			Layout layout3 = Layout.builder()
					.name("테스트3")
					.price(30000)
					.build();
			layoutRepository.save(layout3);

			IntStream.rangeClosed(1, 10).forEach(i -> {
				Product product1 = Product.builder()
						.name("프로덕트" + i)
						.price(1000)
						.layout(layout1)
						.build();
				productRepository.save(product1);
			});

			IntStream.rangeClosed(11, 20).forEach(i -> {
				Product product2 = Product.builder()
						.name("프로덕트" + i)
						.price(2000)
						.layout(layout2)
						.build();
				productRepository.save(product2);
			});

			IntStream.rangeClosed(21, 30).forEach(i -> {
				Product product3 = Product.builder()
						.name("프로덕트" + i)
						.price(3000)
						.layout(layout3)
						.build();
				productRepository.save(product3);
			});
		});
	}

}
