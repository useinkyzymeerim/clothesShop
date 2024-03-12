package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateBuyerDTO;
import com.example.clothesshop.entity.Buyer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BuyerMapper {
  BuyerDto toDto (Buyer buyer);

  BuyerDto toEntity(BuyerDto buyerDto);
  Buyer toEntity(CreateBuyerDTO buyerDto);
  List<BuyerDto> toDtoList(List<Buyer> buyers );
}
