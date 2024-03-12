package com.example.clothesshop.service;

import com.example.clothesshop.dto.CreateProductDto;
import com.example.clothesshop.dto.FindProductByBodyDto;
import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.entity.Product;

import java.util.List;

public interface ProductService {

    CreateProductDto createProduct(CreateProductDto productDto);
    ProductDto findById(Long id);
    List<ProductDto> getAllProducts();
    boolean deleteById(Long id);

    List<ProductDto> findAllByManufacturerId(Long id);

    List<ProductDto> findProductsByColor(String color);

    List<ProductDto> findByBody(FindProductByBodyDto productToFind);
    void decreaseStockQuantity(Product product, Integer quantity);

}
