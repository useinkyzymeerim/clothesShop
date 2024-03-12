package com.example.clothesshop.controller;

import com.example.clothesshop.dto.CreateManufacturerDto;
import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.service.impl.ManufacturerServiceImpl;
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

@Tag(name = "Manufacturer", description = "Тут находятся все роуты связанные с производителем")
@RequiredArgsConstructor
@Controller
@RequestMapping("api/manufacturers")

public class ManufacturerController {

    private final ManufacturerServiceImpl service;
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно сохранено",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для создания нового производителя")
    @PostMapping("/create")
    public ResponseEntity<CreateManufacturerDto> create(@RequestBody CreateManufacturerDto manufacturerDto){

        CreateManufacturerDto createdManufacturer = service.createManufacturer(manufacturerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdManufacturer);
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все записи получены успешно",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Этот роут для получения покупателя по айди")
    @GetMapping("/getById/{id}")
    public ResponseEntity<ManufacturerDto> getById(@PathVariable Long id){
        ManufacturerDto manufacturerDto = service.findById(id);
        if(manufacturerDto != null){
            return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
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
    @Operation(summary = "Этот роут для получения всех покупателей")
    @GetMapping("/getAll")
    public ResponseEntity<List<ManufacturerDto>> getAll(){
        List<ManufacturerDto> manufacturerDtoList = service.getAllManufacturer();
        if(!manufacturerDtoList.isEmpty()){
            return new ResponseEntity<>(manufacturerDtoList, HttpStatus.OK);
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
        if(service.deleteById(id)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
