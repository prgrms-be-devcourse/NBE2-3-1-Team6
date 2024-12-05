package com.example.xmasshop.domain.order.controller;

import com.example.xmasshop.domain.order.dto.OrderResponseDto;
import com.example.xmasshop.domain.order.entity.OrderDetailTO;
import com.example.xmasshop.domain.order.entity.OrdersTO;
import com.example.xmasshop.domain.order.service.OrderService;
import com.example.xmasshop.domain.product.entity.ItemsTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class OrderController {


    @Autowired
    private final OrderService orderService;


    @GetMapping("/")
    public String getMain() {
        return "forward:html/main.html";
    }

    @PostMapping("/")
    @ResponseBody
    public String postMain(HttpServletRequest request,
                           HttpSession session) {
        if (request.getParameter("pw").equals("1234")) {
            session.setAttribute("admin", "T");
            return "true";
        } else {
            return "false";
        }
    }

    @ResponseBody
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrders(){
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/list")
    public String getList() {
        return "forward:html/order/order.html";
    }

    @ResponseBody
    @PostMapping("/pay")
    public ResponseEntity<Map<String, Object>> pay(@RequestBody Map<String, Object> requestBody) {


        String email = (String) requestBody.get("email");
        String phone = (String) requestBody.get("phone");
        String customer = (String) requestBody.get("customer");
        String address = (String) requestBody.get("address");
        String zipcode = (String) requestBody.get("zipcode");
        List<Map<String, Object>> detailList = (List<Map<String, Object>>) requestBody.get("detailList");

        // OrdersTO 생성
        OrdersTO ordersTO = new OrdersTO();
        ordersTO.setEmail(email);
        ordersTO.setPhone(phone);
        ordersTO.setCustomer(customer);
        ordersTO.setAddress(address);
        ordersTO.setZipcode(zipcode);

        List<OrderDetailTO> orderDetails = detailList.stream().map(detail -> {
            OrderDetailTO detailTO = new OrderDetailTO();

            ItemsTO itemsTO = new ItemsTO();
            itemsTO.setId(Integer.parseInt((String) detail.get("product_id")));
            detailTO.setProduct_id(itemsTO);

            detailTO.setQuantity(Integer.parseInt((String) detail.get("quantity")));

            detailTO.setOrder_id(ordersTO);

            return detailTO;
        }).collect(Collectors.toList());

        int result = orderService.saveOrder(ordersTO, orderDetails);
        Map<String, Object> response = new HashMap<>();
        response.put("success", (result > 0));
        return ResponseEntity.ok(response);
    }
}

