package com.example.xmasshop.domain.product.controller;

import com.example.xmasshop.domain.product.entity.ItemClassificationTO;
import com.example.xmasshop.domain.product.entity.ItemsTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ProductController {

    // 민영님 url 맞추기
    @RequestMapping("/")
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

        try {
            Part filePart = request.getPart("productimage");
            System.out.println("productimage: "+ filePart.getSubmittedFileName());
            ItemsTO to = new ItemsTO();
            ItemClassificationTO icto = new ItemClassificationTO();

            icto.setId(productCategory);
            // productCategory에 맞는 name을 반환
//            icto.setName()

            to.setCategory(icto);
        } catch (IOException e) {
            System.out.println("[IO에러]"+e.getMessage());
        } catch (ServletException e) {
            System.out.println("[Servelet에러]"+e.getMessage());
        }

//        ItemsTO to = new ItemsTO();
//        to.setId( request.getParameter("empno") );
//        to.setEname( request.getParameter("ename") );
//        to.setJob( request.getParameter("job") );
//        to.setMgr( request.getParameter("mgr") );
//        to.setHiredate( request.getParameter("hiredate") );
//        to.setSal( request.getParameter("sal") );
//        to.setComm( request.getParameter("comm") );
//        to.setDeptno( request.getParameter("deptno") );
//
//        int flag = empDAO.insert( to );
//
//        return flag;
        return 0;
    }
}
