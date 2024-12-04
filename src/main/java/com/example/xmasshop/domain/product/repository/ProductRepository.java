package com.example.xmasshop.domain.product.repository;

import com.example.xmasshop.domain.product.entity.ItemClassificationTO;
import com.example.xmasshop.domain.product.entity.ItemsTO;
import com.example.xmasshop.domain.product.mapper.ProductMapper;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    @Autowired
    private ProductMapper productMapper;
    public ArrayList<ItemClassificationTO> categoryAll(){
        return productMapper.categoryAll();
    }
    public int insertProduct(ItemsTO itemsTO){
        return productMapper.insertProduct(itemsTO);
    }


}
