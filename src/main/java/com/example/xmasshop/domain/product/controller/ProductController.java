package com.example.xmasshop.domain.product.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @RequestMapping("/")
    public String registerhtml() {
        System.out.println("registerhtml called");
        return "html/admin/register.html";
    }


    @PostMapping("/items")
    public int post(HttpServletRequest request) {
//        EmpTO to = new EmpTO();
//        to.setEmpno( request.getParameter("empno") );
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
