package dev.sangco.layoutproduct.web;

import dev.sangco.layoutproduct.common.ErrorResponse;
import dev.sangco.layoutproduct.service.ProductService;
import dev.sangco.layoutproduct.web.dto.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> sava(@RequestBody @Valid ProductRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorResponse.createErrorResponse(bindingResult));
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid ProductRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorResponse.createErrorResponse(bindingResult));
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id")
    public ResponseEntity<?> delete(@PathVariable String id) {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id")
    public ResponseEntity<?> findById(@PathVariable String id) {

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(@PathVariable String id) {

        return ResponseEntity.ok().build();
    }

}
