package com.example.xmasshop.domain.product.dto;

import com.example.xmasshop.domain.product.entity.ItemsTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryIdDto {
    Integer id;
    String name;
    int categoryId;
    String description;
    Integer price;
    String img_name;

    public static CategoryIdDto from(ItemsTO items){
        return new CategoryIdDto(items.getId(),items.getName(), items.getCategory().getId(), items.getDescription().replaceAll("<:NEWLINE:>","\r\n"),items.getPrice(), items.getImg_name());
    }
}
