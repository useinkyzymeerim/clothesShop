package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.entity.Manufacturer;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.service.TransactionService;
import com.example.clothesshop.service.impl.SalesmanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.service.impl.SalesmanServiceImpl;
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
@Tag(name = "Salesman", description = "Тут находятся все роуты связанные с продавцом")

@RequiredArgsConstructor
@Controller
@RequestMapping("api/salesman")
public class SalesmanController {

    private final SalesmanServiceImpl service;
    private final TransactionService transactionService;
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для создания нового продавца")
    @PostMapping("/create")
    public ResponseEntity<CreateSalesmanDto> create(@RequestBody CreateSalesmanDto salesmanDto){
        CreateSalesmanDto salesmanToCreate = service.createSalesman(salesmanDto);
        if(salesmanToCreate != null){
            return new ResponseEntity<>(salesmanToCreate, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения продавца по айди")
    @GetMapping("/getById/{id}")
    public ResponseEntity<SalesmanDto> getById(@PathVariable Long id){
        SalesmanDto salesman = service.getById(id);
        if(salesman != null){
            return new ResponseEntity<>(salesman, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения всех продавцов")
    @GetMapping("/getAll")
    public ResponseEntity<List<SalesmanDto>> getAll(){
        List<SalesmanDto> salesmanDtoList = service.getAll();
        if(!salesmanDtoList.isEmpty()){
            return new ResponseEntity<>(salesmanDtoList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно удалено",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для удаления продавца по айди")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestParam Long id){

        if(service.deleteById(id)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получение всех продуктов у определенного продовца ")
    @GetMapping("/products/{salesmanId}")
    public ResponseEntity<List<Product>> getAllProductsOfSalesman(@PathVariable Long salesmanId) {
        List<Product> products = transactionService.getAllProductsOfSalesman(salesmanId);
        return ResponseEntity.ok(products);
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получение общее количесто продуктов у определенного продовца ")
    @GetMapping("/total-products/{salesmanId}")
    public ResponseEntity<Long> getTotalProductsOfSalesman(@PathVariable Long salesmanId) {
        long totalProducts = transactionService.getTotalProductsOfSalesman(salesmanId);
        return ResponseEntity.ok(totalProducts);
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получение цены на продукта у определенного продовца ")
    @GetMapping("/product-price/{salesmanId}/{productId}")
    public ResponseEntity<Double> getProductPriceOfSalesman(@PathVariable Long salesmanId, @PathVariable Long productId) {
        double productPrice = transactionService.getProductPriceOfSalesman(salesmanId, productId);
        return ResponseEntity.ok(productPrice);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут получает общее кол-во денег, который продовец потратиль на товары")
    @GetMapping("/total-spent/{salesmanId}")
    public ResponseEntity<Double> getTotalSpentBySalesman(@PathVariable Long salesmanId) {
        double totalSpent = transactionService.getSpentBySalesman(salesmanId);
        return ResponseEntity.ok(totalSpent);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получений всех производителей, который продовец брал товары")
    @GetMapping("/manufacturers/{salesmanId}")
    public ResponseEntity<List<Manufacturer>> getManufacturersOfSalesmanProducts(@PathVariable Long salesmanId) {
            List<Manufacturer> manufacturers = transactionService.getAllManufacturerOfSalesmanProduct(salesmanId);
        return ResponseEntity.ok(manufacturers);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получений всех товаров продовца и добавляет в конец продуктов букву s ")
    @GetMapping("/product-names/{salesmanId}")
    public ResponseEntity<List<String>> getProductNamesWithS(@PathVariable Long salesmanId) {
        List<String> productNames = transactionService.productNameWithS(salesmanId);
        return ResponseEntity.ok(productNames);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получений всех товаров продовца и делает скидку на 50% ")
    @GetMapping("/discounted-prices/{salesmanId}")
    public ResponseEntity<List<Integer>> getDiscountedPricesOfSalesman(@PathVariable Long salesmanId) {
        List<Integer> discountedPrices = transactionService.discountedPrice(salesmanId);
        return ResponseEntity.ok(discountedPrices);
    }
}
