package com.example.xmasshop.domain.product.service;

import com.example.xmasshop.domain.product.dto.ItemResponseDto;
import com.example.xmasshop.domain.product.entity.ItemsTO;
import com.example.xmasshop.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public List<ItemResponseDto> getAllItems(){
        return productRepository.findAllItems().stream().map(ItemResponseDto::from).collect(Collectors.toList());
    }

    public ItemResponseDto getItem(int id) {
        return ItemResponseDto.from(productRepository.findOneItemById(id));
    }

    public void deleteItem(Integer id){
        productRepository.deleteItem(id);
    }

}
