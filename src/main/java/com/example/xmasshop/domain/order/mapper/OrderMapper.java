package com.example.xmasshop.domain.order.mapper;

import com.example.xmasshop.domain.order.entity.OrderDetailTO;
import com.example.xmasshop.domain.order.entity.OrdersTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrdersTO> selectAllOrders();

    List<OrderDetailTO> selectOrderDetailsById(Integer id);
}
