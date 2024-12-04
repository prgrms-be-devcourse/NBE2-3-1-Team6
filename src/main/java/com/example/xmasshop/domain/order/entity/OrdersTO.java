package com.example.xmasshop.domain.order.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

//주문Table
@Alias( value = "ordersto" )
@Getter
@Setter
public class OrdersTO {
    Integer id;
    String customer;
    String email;
    String phone;
    String address;
    String zipcode;
    LocalDateTime date;

}