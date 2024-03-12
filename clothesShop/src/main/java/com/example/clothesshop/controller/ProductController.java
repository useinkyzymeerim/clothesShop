package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateProductDto;
import com.example.clothesshop.dto.FindProductByBodyDto;
import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.clothesshop.dto.CreateProductDto;
import com.example.clothesshop.dto.FindProductByBodyDto;
import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Product", description = "Тут находятся все роуты связанные с проуктом")

@Controller
@RequiredArgsConstructor
@RequestMapping("api/products/")

public class ProductController {

    private final ProductServiceImpl service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для создания нового продукта")
    @PostMapping("create")
    public ResponseEntity<CreateProductDto> create(@RequestBody CreateProductDto productDto) {
        CreateProductDto createdProduct = service.createProduct(productDto);
        if (createdProduct != null) {
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения  продукта по айди")
    @GetMapping("getById/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        ProductDto productDto = service.findById(id);
        if (productDto != null) {
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения всех продуктов")
    @GetMapping("getAll")
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productDtoList = service.getAllProducts();
        if (!productDtoList.isEmpty()) {
            return new ResponseEntity<>(productDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения пролукта по айди производителя")
    @GetMapping("findAllByManufacturerId/{id}")
    public ResponseEntity<List<ProductDto>> findAllByManufacturerId(@PathVariable Long id) {
        List<ProductDto> products = service.findAllByManufacturerId(id);

        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения по цвету продукта")
    @GetMapping("findProductsByColor")
    public ResponseEntity<List<ProductDto>> findProductsByColor(@RequestParam String color) {
        List<ProductDto> products = service.findProductsByColor(color);

        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения продукта по нескольким параметрам")
    @PostMapping("findByBody")
    public ResponseEntity<List<ProductDto>> findByBody(@RequestBody FindProductByBodyDto productToFind) {
        List<ProductDto> products = service.findByBody(productToFind);

        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для удаления продукта по айди")
    @DeleteMapping("delete")
    public ResponseEntity<Boolean> delete(@RequestParam Long id) {
        if (service.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
