package com.example.xmasshop.domain.order.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

//주문Table
@Alias( value = "ordersto" )
@Getter
@Setter
@ToString
public class OrdersTO {
    private Integer id;
    private String customer;
    private String email;
    private String phone;
    private String address;
    private String zipcode;
    private LocalDateTime date;

}