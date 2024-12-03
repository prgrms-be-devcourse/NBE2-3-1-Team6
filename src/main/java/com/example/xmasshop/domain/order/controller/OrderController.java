package com.example.xmasshop.domain.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class OrderController {
    @RequestMapping("/")
    public String registerhtml() {
        System.out.println("registerhtml called");
        return "html/admin/register.html";
    }

}
