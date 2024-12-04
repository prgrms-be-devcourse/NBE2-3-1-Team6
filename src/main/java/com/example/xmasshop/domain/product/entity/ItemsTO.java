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
    String description;
    ItemClassificationTO category;
    Integer price;
    String img_name;

    public ItemsTO(String name){this.name=name;}
}
