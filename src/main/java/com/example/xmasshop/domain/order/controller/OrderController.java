package com.example.xmasshop.domain.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @GetMapping("/")
    public String getMain() {
        return "html/main.html";
    }

    @PostMapping("/")
    public String postMain(HttpServletRequest request) {

        if (request.getParameter("pw").equals("1234")) {
            // 관리자 페이지로 리다이렉트
            return "true";
        } else {
            return "false";
        }
    }
}
