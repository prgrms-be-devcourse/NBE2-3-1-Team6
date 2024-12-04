package com.example.xmasshop.domain.product.controller;

import com.example.xmasshop.domain.product.entity.ItemClassificationTO;
import com.example.xmasshop.domain.product.entity.ItemsTO;
import com.example.xmasshop.domain.product.repository.ProductRepository;
import com.example.xmasshop.util.product.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ProductController {

    @Autowired
    Utils utils;

    @Autowired
    ProductRepository productRepository;

    // 민영님 url 맞추기
    @RequestMapping("/register")
    public String registerhtml() {
        System.out.println("registerhtml called");
        return "html/admin/register.html";
    }

    // 민영님 url 맞추기
    @PostMapping("/items")
    @ResponseBody
    public int post(HttpServletRequest request) {

        String productName = request.getParameter("productname");
        int productPrice = Integer.parseInt(request.getParameter("productprice"));
        int productCategory = Integer.parseInt(request.getParameter("productcategory"));
        String productDescription = request.getParameter("productdescription");
        System.out.println("productName : " + productName);
        System.out.println("productPrice : " + productPrice);
        System.out.println("productCategory : " + productCategory);
        System.out.println("productDescription : " + productDescription);

        ItemsTO to = new ItemsTO();
        ItemClassificationTO icto = new ItemClassificationTO();

        try {
            Part filePart = request.getPart("productimage");

            icto.setId(productCategory);
            icto.setName(utils.getCategoryName(productCategory));
            to.setName(productName);
            to.setDescription(productDescription);
            to.setPrice(productPrice);
            to.setCategory(icto);
            to.setImg_name(filePart.getSubmittedFileName());

            // 업로드 파일 저장
            // 업로드 경로 설정
            String uploadPath = "C:\\Java\\TeamProjects\\xmasShop\\src\\main\\resources\\static\\imgs";
            String filePath = uploadPath+ File.separator+ filePart.getSubmittedFileName();
            filePart.write(filePath);

        } catch (IOException e) {
            System.out.println("[IO에러]"+e.getMessage());
        } catch (ServletException e) {
            System.out.println("[Servelet에러]"+e.getMessage());
        }

        int flag = productRepository.insertProduct(to);

        return flag;

    }
}
