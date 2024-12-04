package com.example.xmasshop.domain.product.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

// 상품 테이블
@Alias( value = "itemsto" )
@Getter
@Setter
public class ItemsTO {
    Integer id;
    String name;
    ItemClassificationTO category;
    Integer price;
    String img_name;
    String description;
}