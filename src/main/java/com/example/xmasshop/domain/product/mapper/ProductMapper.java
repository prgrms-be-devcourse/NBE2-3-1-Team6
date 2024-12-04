package com.example.xmasshop.domain.product.mapper;

import com.example.xmasshop.domain.product.entity.ItemClassificationTO;
import com.example.xmasshop.domain.product.entity.ItemsTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.ArrayList;
import java.util.List;


@Mapper
public interface ProductMapper {
    ArrayList<ItemClassificationTO> categoryAll();
    int insertProduct(ItemsTO itemsTO);

    List<ItemsTO> selectAll();

    void deleteItem(Integer id);

    ItemsTO selectOneItem(Integer id);
}
