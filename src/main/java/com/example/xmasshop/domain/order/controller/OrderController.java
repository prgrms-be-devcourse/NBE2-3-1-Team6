package com.example.xmasshop.domain.order.controller;

import com.example.xmasshop.domain.order.dto.OrderResponseDto;
import com.example.xmasshop.domain.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {


    @Autowired
    private final OrderService orderService;


    @GetMapping("/")
    public String getMain() {
        return "html/main.html";
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
        return "html/order/order.html";
    }
}

