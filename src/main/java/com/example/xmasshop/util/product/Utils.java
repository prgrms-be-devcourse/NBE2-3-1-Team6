package com.example.xmasshop.util.product;

import com.example.xmasshop.domain.product.entity.ItemClassificationTO;
import com.example.xmasshop.domain.product.repository.ProductRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    @Autowired
    ProductRepository productRepository;

    public String getCategoryName(int categoryId){
        ArrayList<ItemClassificationTO> list = productRepository.categoryAll();

        for(ItemClassificationTO icto : list){
            if(icto.getId().equals(categoryId)){
                return icto.getName();
            }
        }
        return "no such category";
    }
}
