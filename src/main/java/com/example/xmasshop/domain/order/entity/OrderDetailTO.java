package com.example.xmasshop.domain.order.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

//주문상세Table
@Alias( value = "orderdetailto" )
@Getter
@Setter
public class OrderDetailTO {
    private String order_id;
    private String product_id;
    private String quantity;
}
