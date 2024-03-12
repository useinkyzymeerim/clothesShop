package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.CreateBuyerDTO;
import com.example.clothesshop.dto.CreateProductDto;
import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
    List<ProductDto> toDtoList(List<Product> products);
    Product toEntity(CreateProductDto createProduct);
}
