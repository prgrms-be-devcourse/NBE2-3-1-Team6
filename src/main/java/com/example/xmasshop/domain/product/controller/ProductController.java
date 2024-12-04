package com.example.xmasshop.domain.product.controller;

import com.example.xmasshop.domain.product.dto.ItemResponseDto;
import com.example.xmasshop.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @GetMapping("/items")
    @ResponseBody
    public ResponseEntity<List<ItemResponseDto>> getAllItems(){
        return ResponseEntity.ok(productService.getAllItems());
    }

    @ResponseBody
    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Integer id){
        System.out.println(id);
        productService.deleteItem(id);
        return ResponseEntity.ok(String.valueOf(id));
    }

    @GetMapping("/page")
    public String getPage(){
        return "html/admin/admin.html";
    }

}
