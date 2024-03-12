package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.dto.CreateBuyerDTO;
import com.example.clothesshop.dto.CreateManufacturerDto;
import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.entity.Manufacturer;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.exeption.NotFoundExeption;
import com.example.clothesshop.mapper.BuyerMapper;
import com.example.clothesshop.repo.BuyerRepo;
import com.example.clothesshop.repo.TransactionRepo;
import com.example.clothesshop.service.BuyerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    private  final BuyerRepo buyerRepo;
    private  final BuyerMapper buyerMapper;
    @Override
    public CreateBuyerDTO createBuyer(CreateBuyerDTO buyer) {

            try {
             buyerRepo.save(buyerMapper.toEntity(buyer));
            } catch (Exception e) {
                log.error(e.getStackTrace().toString());
            }
            return buyer;

    }

    @Override
    public BuyerDto findById(Long id) {
        Buyer buyer = buyerRepo.findByDeleteDateIsNullAndId(id);
        return  buyerMapper.toDto(buyer)    ;
    }

    @Override
    public List<BuyerDto> getAll() {
        return buyerMapper.toDtoList(buyerRepo.findAll());
    }




    @Override
    public boolean deleteById (Long id){
        Buyer buyer = buyerRepo.findByDeleteDateIsNullAndId(id);

        if(buyer != null){
            buyer.setDeleteDate(new Date(System.currentTimeMillis()));
            buyerRepo.save(buyer);
            return true;
        }
        else{
            return false;
        }
    }
}
