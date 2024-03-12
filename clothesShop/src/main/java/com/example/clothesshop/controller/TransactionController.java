package com.example.clothesshop.controller;
import com.example.clothesshop.dto.TransactionDTO;
import com.example.clothesshop.entity.Transaction;
import com.example.clothesshop.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import org.springframework.stereotype.Controller;


@Tag(name = "Transaction", description = "Тут находятся все роуты связанные с транзакциями")

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для создания новой транзакции")
    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> create(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO transaction = transactionService.createTransaction(transactionDTO);
        if (transaction != null){
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения транзакции по айди")
    @GetMapping("/getById/{id}")
    public ResponseEntity<TransactionDTO> getById(@PathVariable Long id){
        TransactionDTO transactionDTO = transactionService.findById(id);
        if(transactionDTO != null){
            return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
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
    @Operation(summary = "Этот роут для получения всех транзакции")
    @GetMapping("/getAll")
    public ResponseEntity<List<TransactionDTO>> getAll(){
        List<TransactionDTO> transactionDTOS = transactionService.getAll();
        if(!transactionDTOS.isEmpty()){
            return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
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
    @Operation(summary = "Этот роут для совершения  транзакции")
    @PostMapping("/sellProduct")
    public ResponseEntity<String> sellProduct(@RequestBody TransactionDTO transactionDTO,@RequestParam int quantity) {
        try {
            transactionService.sellProduct(transactionDTO,quantity);
            return ResponseEntity.ok("Transaction completed successfully");
        } catch (IllegalAccessError e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения транзакции по айди продавца")
    @GetMapping("/getAllBySalesmanId/{id}")
    public List<Transaction> getAllBySalesmanId(@PathVariable Long id) {
        return transactionService.findAllBySalesmanId(id);

    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения транзакции по айди покупателя")
    @GetMapping("getAllByBuyerId/{id}")
    public List<Transaction> getAllByBuyerId(@PathVariable Long id) {
        return transactionService.findAllByBuyerId(id);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно удалено",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для удаления транзакции по айди")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestParam Long id){
        if(transactionService.deleteById(id)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }


}


