package com.example.xmasshop.domain.order.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

//주문Table
@Alias( value = "ordersto" )
@Getter
@Setter
public class OrdersTO {
    private String id;
    private String email;
    private String phone;
    private String customer;
    private String address;
    private String zipcode;
    private String date;
}
