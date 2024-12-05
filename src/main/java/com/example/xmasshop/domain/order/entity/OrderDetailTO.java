package com.example.xmasshop.domain.order.entity;

import com.example.xmasshop.domain.product.entity.ItemsTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

//주문상세Table
@Alias( value = "orderdetailto" )
@Getter
@Setter
@ToString
public class OrderDetailTO {
    private Integer id;
    private OrdersTO order_id;
    private ItemsTO product_id;
    private Integer quantity;
}
