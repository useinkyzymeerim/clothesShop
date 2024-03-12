package com.example.clothesshop.service;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateBuyerDTO;
import com.example.clothesshop.dto.CreateManufacturerDto;
import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.repo.BuyerRepo;

import java.util.List;

public interface BuyerService {
    BuyerDto findById(Long id);
    CreateBuyerDTO createBuyer(CreateBuyerDTO buyer);
    List<BuyerDto> getAll();
    boolean deleteById(Long id);
}
