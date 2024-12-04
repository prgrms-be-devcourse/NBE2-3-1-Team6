package com.example.xmasshop.domain.order.dto;

import com.example.xmasshop.domain.order.entity.OrdersTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class OrderResponseDto {

    String name;
    String email;
    String address;
    String zipcode;
    String productName;
    Integer quantity;
    String orderDate;
    LocalDate deliveryDate;

    public static OrderResponseDto from(OrdersTO ot, String product, Integer quantity){
        LocalDate deliveryDate = ot.getDate().toLocalTime().getHour()<14?ot.getDate().toLocalDate():ot.getDate().plusDays(1).toLocalDate();

        String dateWithNewForamt = ot.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        return new OrderResponseDto(ot.getCustomer(), ot.getEmail(),ot.getAddress(),ot.getZipcode(),product,quantity,dateWithNewForamt,deliveryDate);
    }

}
