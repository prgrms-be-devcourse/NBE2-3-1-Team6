package com.example.xmasshop.domain.order.entity;

import com.example.xmasshop.domain.product.entity.ItemsTO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

//주문상세Table
@Alias( value = "orderdetailto" )
@Getter
@Setter
public class OrderDetailTO {
    Integer id;
    OrdersTO order_id;
    ItemsTO product_id;
    Integer quantity;
}
