package com.example.xmasshop.domain.product.dto;

import com.example.xmasshop.domain.product.entity.ItemsTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemResponseDto {
    Integer id;
    String name;
    String category;
    String description;
    Integer price;
    String imgUrl;

    public static ItemResponseDto from(ItemsTO items){
        return new ItemResponseDto(items.getId(),items.getName(), items.getCategory().getName(), items.getDescription(),items.getPrice(), items.getImg_name());
    }
}
