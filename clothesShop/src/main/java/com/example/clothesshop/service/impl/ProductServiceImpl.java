package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.CreateProductDto;
import com.example.clothesshop.dto.FindProductByBodyDto;
import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.mapper.ProductMapper;
import com.example.clothesshop.repo.ProductRepo;
import com.example.clothesshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepo repo;
    private final ProductMapper productMapper;

    @Override
    public CreateProductDto createProduct(CreateProductDto productDto) {
        try {
            repo.save(productMapper.toEntity(productDto));
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
        }
        return productDto;
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = repo.findByDeleteDateIsNullAndId(id);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productMapper.toDtoList(repo.findAll());
    }

    @Override
    public boolean deleteById(Long id) {
        Product product = repo.findByDeleteDateIsNullAndId(id);
        if(product != null){
            product.setDeleteDate(new Date(System.currentTimeMillis()));
            repo.save(product);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<ProductDto> findAllByManufacturerId(Long id) {
        List<Product> products = repo.findAllByDeleteDateIsNullAndManufacturerId(id);

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : products){
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .manufacturer(product.getManufacturer())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public List<ProductDto> findProductsByColor(String color) {
        List<Product> products = repo.findProductsByColor(color);

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : products){
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .manufacturer(product.getManufacturer())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    public List<ProductDto> findByBody(FindProductByBodyDto productToFind){
        List<Product> products = repo.findByBody(productToFind.getPrices(),
                                                 productToFind.getColors(),
                                                 productToFind.getManufacturers());

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : products){
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .manufacturer(product.getManufacturer())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public void decreaseStockQuantity(Product product, Integer quantity) {

    }

}
