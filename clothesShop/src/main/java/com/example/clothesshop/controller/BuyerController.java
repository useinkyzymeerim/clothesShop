package com.example.clothesshop.controller;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateBuyerDTO;
import com.example.clothesshop.dto.CreateManufacturerDto;
import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.service.BuyerService;
import com.example.clothesshop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
;

import java.util.List;
@Tag(name = "Buyer", description = "Тут находятся все роуты связанные с покупателям")
@Controller
@RequiredArgsConstructor
@RequestMapping("api/buyers")
public class BuyerController {
    private final BuyerService buyerService;
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно сохранено",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для создания нового покупателя")

    @PostMapping(("/create"))
    public ResponseEntity<CreateBuyerDTO> create(@RequestBody CreateBuyerDTO buyer) {
        CreateBuyerDTO buyer1 = buyerService.createBuyer(buyer);
        if (buyer1 != null){
            return new ResponseEntity<>(buyer1, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для для получения покупателя по айди")
    @GetMapping("/{id}")
    public ResponseEntity<BuyerDto> getById(@PathVariable Long id){
        BuyerDto buyer = buyerService.findById(id);
        if(buyer != null){
            return new ResponseEntity<>(buyer, HttpStatus.OK);
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
    @Operation(summary = "Этот роут для получения всех покупателей  ")
    @GetMapping()
    public ResponseEntity<List<BuyerDto>> getAll(){
        List<BuyerDto> buyers = buyerService.getAll();
        if(!buyers.isEmpty()){
            return new ResponseEntity<>(buyers, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно удалено",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для удаления  покупателя по айди")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestParam Long id){
        if(buyerService.deleteById(id)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}
