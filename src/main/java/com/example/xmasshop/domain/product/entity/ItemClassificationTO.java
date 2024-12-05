package com.example.xmasshop.domain.product.entity;


import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

//분류테이블
@Alias( value = "itemclassificationto" )
@Getter
@Setter
public class ItemClassificationTO {
    private Integer id;
    private String name;
}
