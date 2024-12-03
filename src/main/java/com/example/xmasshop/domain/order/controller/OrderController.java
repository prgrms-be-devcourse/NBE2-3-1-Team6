package com.example.xmasshop.domain.order.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @GetMapping("/")
    public String getMain() {
        return "html/main.html";
    }

    @PostMapping("/")
    public String postMain(HttpServletRequest request) {

        if (request.getParameter("pw").equals("1234")) {
            // 여기서 관리자페이지 redirect 하기
            return "true";
        } else {
            return "false";
        }
    }

}