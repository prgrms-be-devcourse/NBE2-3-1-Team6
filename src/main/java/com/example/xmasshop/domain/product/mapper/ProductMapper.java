package com.example.xmasshop.domain.product.mapper;

import com.example.xmasshop.domain.product.entity.ItemClassificationTO;
import com.example.xmasshop.domain.product.entity.ItemsTO;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    ArrayList<ItemClassificationTO> categoryAll();
    int insertProduct(ItemsTO itemsTO);
}
