package com.example.xmasshop.domain.product.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

// 상품 테이블
@Alias( value = "itemsto" )
@Getter
@Setter
@ToString
public class ItemsTO {
    private Integer id;
    private String name;
    private String description;
    private ItemClassificationTO category;
    private Integer price;
    private String img_name;

    public ItemsTO(String name){this.name=name;}
}
