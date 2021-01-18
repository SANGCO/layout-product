package dev.sangco.layoutproduct.web.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequestDto {

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    @NotBlank
    private String layoutId;

}
