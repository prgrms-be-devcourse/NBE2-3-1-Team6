package com.example.xmasshop.domain.product.mapper;

import com.example.xmasshop.domain.product.entity.ItemsTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ItemsTO> selectAll();

    void deleteItem(Integer id);

    ItemsTO selectOneItem(Integer id);
}
