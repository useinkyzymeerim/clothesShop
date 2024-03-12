package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.entity.Salesman;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalesmanMapper {
      SalesmanDto toDto(Salesman salesman);
      Salesman toEntity(SalesmanDto salesmanDto);
      List<SalesmanDto> toDtoList(List<Salesman> createSalesman);
      Salesman toEntity(CreateSalesmanDto createSalesman);
}
