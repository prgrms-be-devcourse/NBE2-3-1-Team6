package com.example.xmasshop.domain.product.controller;

import com.example.xmasshop.domain.product.dto.CategoryIdDto;
import com.example.xmasshop.domain.product.dto.ItemResponseDto;
import com.example.xmasshop.domain.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.xmasshop.domain.product.entity.ItemClassificationTO;
import com.example.xmasshop.domain.product.entity.ItemsTO;
import com.example.xmasshop.domain.product.repository.ProductRepository;
import com.example.xmasshop.util.product.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @Autowired
    Utils utils;

    @Autowired
    ProductRepository productRepository;

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
        return "forward:/html/admin/admin.html";
    }

    @RequestMapping("/register")
    public String registerhtml() {
        System.out.println("registerhtml called");
        return "forward:/html/admin/register.html";
    }

    @RequestMapping("/modify/{id}")
    public String modifyhtml(@PathVariable("id") String id, Model model) {
        System.out.println("id:"+id);
        System.out.println("modifyhtml called");

        ItemsTO itemsto = productRepository.findOneItemById(Integer.parseInt(id));
        CategoryIdDto categoryIdDto = CategoryIdDto.from(itemsto);

        model.addAttribute("categoryIdDto", categoryIdDto);

        return "/admin/modify";
    }




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


        ItemsTO to = new ItemsTO(productName);
        ItemClassificationTO icto = new ItemClassificationTO();

        try {
            Part filePart = request.getPart("productimage");
            String fileName = filePart.getSubmittedFileName();

            //파일명 중복방지
            String tempName = fileName.substring(0, fileName.lastIndexOf("."));
            String ext = fileName.substring(fileName.lastIndexOf("."));
            fileName = tempName +"_"+System.nanoTime()+ ext;

            icto.setId(productCategory);
            icto.setName(utils.getCategoryName(productCategory));
            to.setDescription(productDescription.replaceAll("\r\n", "<:NEWLINE:>"));
            to.setPrice(productPrice);
            to.setCategory(icto);
            to.setImg_name(fileName);

            // 업로드 파일 저장
            // 업로드 경로 설정
            String basePath = System.getProperty("user.dir");
            String uploadPath = basePath+"\\src\\main\\resources\\static\\imgs";


            // 업로드 경로가 존재하지 않는 경우 생성
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                if (uploadDir.mkdirs()) {
                    System.out.println("Upload directory created: " + uploadPath);
                } else {
                    throw new RuntimeException("Failed to create upload directory: " + uploadPath);
                }
            }

            String filePath = uploadPath+ File.separator+ fileName;
            filePart.write(filePath);

        } catch (IOException e) {
            System.out.println("[IO에러]"+e.getMessage());
        } catch (ServletException e) {
            System.out.println("[Servelet에러]"+e.getMessage());
        }

        int flag = productRepository.insertProduct(to);


        return flag;
    }

    @PutMapping("/items/{id}")
    @ResponseBody
    public int put(@PathVariable("id") Integer id, HttpServletRequest request) {

        System.out.println("putmapping id: "+ id);
        int productId = id;
        String productName = request.getParameter("productname");
        int productPrice = Integer.parseInt(request.getParameter("productprice"));
        int productCategory = Integer.parseInt(request.getParameter("productcategory"));
        String productDescription = request.getParameter("productdescription");
        String oldFileName = request.getParameter("oldFileName");
        int flag = 0;
        System.out.println("productId : " + productId);
        System.out.println("productName : " + productName);
        System.out.println("productPrice : " + productPrice);
        System.out.println("productCategory : " + productCategory);
        System.out.println("productDescription : " + productDescription);
        System.out.println("oldFileName : " + oldFileName);

        ItemsTO to = new ItemsTO(productName);
        ItemClassificationTO icto = new ItemClassificationTO();

        try {
            Part filePart = request.getPart("productimage");
            String newFileName = filePart.getSubmittedFileName();
            int newFileSize = Integer.parseInt(String.valueOf(filePart.getSize()));

            icto.setId(productCategory);
            icto.setName(utils.getCategoryName(productCategory));
            to.setId(productId);
            to.setName(productName);
            to.setDescription(productDescription.replaceAll("\r\n", "<:NEWLINE:>"));
            to.setPrice(productPrice);
            to.setCategory(icto);

            if(newFileSize==0 && newFileName.equals("")){
                // 사용자가 파일을 새로 업로드x -> 기존 파일 유지
                // 파일 수정 성공시 flag = 1
                flag = productRepository.updateProductOldImage(to);

            } else{
                // 사용자가 파일을 새로운 파일을 업로드한 겨우
                // -> 기존 파일 업로드 파일 폴더에서 삭제후
                // -> 새로운 파일 업로드 파일 폴더에 올리고 데이터베이스 img name 수정
                System.out.println("file exists");

                // 파일명 중복방지
                String tempName = newFileName.substring(0, newFileName.lastIndexOf("."));
                String ext = newFileName.substring(newFileName.lastIndexOf("."));
                newFileName = tempName +"_"+System.nanoTime()+ ext;

                System.out.println("tempName: "+ tempName);
                System.out.println("ext: "+ ext);
                System.out.println("newFileName: "+ newFileName);


                // 업로드 파일 저장
                // 업로드 경로 설정
                String basePath = System.getProperty("user.dir");
                String uploadPath = basePath+"\\src\\main\\resources\\static\\imgs";
                System.out.println(basePath);
                System.out.println(uploadPath);

                // 업로드 경로가 존재하지 않는 경우 생성
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    if (uploadDir.mkdirs()) {
                        System.out.println("Upload directory created: " + uploadPath);
                    } else {
                        throw new RuntimeException("Failed to create upload directory: " + uploadPath);
                    }
                }

                String filePath = uploadPath+ File.separator+ newFileName;
                filePart.write(filePath);

                to.setImg_name(newFileName);
                // 파일 수정 성공시 flag = 1
                flag = productRepository.updateProductNewImage(to);

                //기존파일삭제
                //기존파일도 존재하고 새업로드 파일도 존재하는지 확인
                if(!oldFileName.equals("")){
                    new File( "C:\\Java\\TeamProjects\\xmasShop\\src\\main\\resources\\static\\imgs", oldFileName ).delete();                }
            }

        } catch (IOException e) {
            System.out.println("[IO에러]"+e.getMessage());
        } catch (ServletException e) {
            System.out.println("[Servelet에러]"+e.getMessage());
        }

        System.out.println("flag: " + flag);
        return flag;
    }

}
