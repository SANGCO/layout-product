package dev.sangco.layoutproduct.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ProductRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private int price;

    @NotBlank
    private String layoutId;

}
