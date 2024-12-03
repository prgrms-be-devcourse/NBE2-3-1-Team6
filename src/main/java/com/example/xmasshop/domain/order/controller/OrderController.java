package com.example.xmasshop.domain.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @GetMapping("/")
    public String getMain() {
        return "html/main.html";
    }

    @PostMapping("/")
    @ResponseBody
    public String postMain(HttpServletRequest request) {

        if (request.getParameter("pw").equals("1234")) {
            // 여기서 관리자페이지 redirect 하기
            return "true";
        } else {
            return "false";
        }
    }

    @GetMapping("/products")
    public String getItems(Model model) {
//        model.addAttribute("lists")
        return "html/order/product.html";
    }

    @GetMapping("/orders")
    public String getOrders() {
        return "html/order/order.html";
    }
}