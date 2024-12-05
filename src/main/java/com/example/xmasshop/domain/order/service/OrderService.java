package com.example.xmasshop.domain.order.service;

import com.example.xmasshop.domain.order.dto.OrderResponseDto;
import com.example.xmasshop.domain.order.entity.OrderDetailTO;
import com.example.xmasshop.domain.order.entity.OrdersTO;
import com.example.xmasshop.domain.order.repository.OrderRepository;
import com.example.xmasshop.domain.product.entity.ItemsTO;
import com.example.xmasshop.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public List<OrderResponseDto> getOrders(){

        List<OrderResponseDto> ordersResponse = new ArrayList<>();

        List<OrdersTO> ordersTOS = orderRepository.getOrders();

        for(OrdersTO ot : ordersTOS) {
            List<OrderDetailTO> ods =  orderRepository.getOrderDetails(ot.getId());
            for(OrderDetailTO odt : ods){
                String productName = Optional.ofNullable(odt.getProduct_id()).orElseGet(()-> new ItemsTO("상품정보없음")).getName();
                OrderResponseDto ort = OrderResponseDto.from(ot,productName, Objects.requireNonNull(odt).getQuantity());
                ordersResponse.add(ort);
            }
        }
        return ordersResponse;

    }

    public int saveOrder(OrdersTO ordersTO, List<OrderDetailTO> orderDetails) {
        int flag = 0;

        orderRepository.insertOrders(ordersTO);

        for (OrderDetailTO detail : orderDetails) {
            detail.setOrder_id(ordersTO);
            flag = orderRepository.insertDetailOrders(detail);
        }
        return flag;
    }

}
