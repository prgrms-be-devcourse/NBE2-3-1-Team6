package com.example.xmasshop.domain.product.repository;

import com.example.xmasshop.domain.product.entity.ItemsTO;
import com.example.xmasshop.domain.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    ProductMapper productMapper;

    public List<ItemsTO> findAllItems(){
        return productMapper.selectAll();

    }

    public ItemsTO findOneItemById(Integer id){
        return productMapper.selectOneItem(id);
    }

    public void deleteItem(Integer id){
        productMapper.deleteItem(id);
    }


}
