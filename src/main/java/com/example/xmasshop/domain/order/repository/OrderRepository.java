package com.example.xmasshop.domain.order.repository;

import com.example.xmasshop.domain.order.entity.OrderDetailTO;
import com.example.xmasshop.domain.order.entity.OrdersTO;
import com.example.xmasshop.domain.order.mapper.OrderMapper;
import com.example.xmasshop.domain.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    @Autowired
    private final OrderMapper orderMapper;

    public List<OrdersTO> getOrders(){
        return orderMapper.selectAllOrders();
    }

    public List<OrderDetailTO> getOrderDetails(Integer id){
        return orderMapper.selectOrderDetailsById(id);
    }

    public int insertOrders(OrdersTO ordersTO){ return orderMapper.insertOrders(ordersTO); }

//    public int insertDetailOrders(OrderDetailTO orderDetailTO){ return orderMapper.insertDetailOrders(orderDetailTO); }
}
