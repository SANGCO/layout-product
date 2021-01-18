package dev.sangco.layoutproduct.web;

import dev.sangco.layoutproduct.common.ErrorResponse;
import dev.sangco.layoutproduct.service.ProductService;
import dev.sangco.layoutproduct.web.dto.ProductRequestDto;
import dev.sangco.layoutproduct.web.dto.ProductResponseDto;
import dev.sangco.layoutproduct.web.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
        ProductResponseDto responseDto = productService.save(requestDto);
        return ResponseEntity.created(URI.create(String.format("/products/%s", responseDto.getId()))).body(new Result<ProductResponseDto>(responseDto));
    }

    @PutMapping("/{id")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid ProductRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorResponse.createErrorResponse(bindingResult));
        }
        productService.update(id, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id")
    public ResponseEntity<?> delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id")
    public ResponseEntity<?> findById(@PathVariable String id) {
        ProductResponseDto responseDto = productService.findById(id);
        return ResponseEntity.ok().body(new Result<ProductResponseDto>(responseDto));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<ProductResponseDto> responseDtoList = productService.findAll();
        return ResponseEntity.ok().body(new Result<List<ProductResponseDto>>(responseDtoList));
    }

}
