package com.example.xmasshop.domain.product.repository;

import com.example.xmasshop.domain.product.entity.ItemClassificationTO;
import com.example.xmasshop.domain.product.entity.ItemsTO;
import com.example.xmasshop.domain.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private ProductMapper productMapper;

    public ArrayList<ItemClassificationTO> categoryAll(){
        return productMapper.categoryAll();
    }

    public int insertProduct(ItemsTO itemsTO) {
        return productMapper.insertProduct(itemsTO);
    }

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
