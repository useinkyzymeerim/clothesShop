package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.CreateSalesmanDto;
import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.entity.Salesman;
import com.example.clothesshop.entity.Transaction;
import com.example.clothesshop.mapper.SalesmanMapper;
import com.example.clothesshop.repo.SalesmanRepo;
import com.example.clothesshop.service.SalesmanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class SalesmanServiceImpl implements SalesmanService {

    private final SalesmanRepo repo;
    private final SalesmanMapper salesmanMapper;
    @Override
    public CreateSalesmanDto createSalesman(CreateSalesmanDto salesmanToCreate) {

        try{
            repo.save(salesmanMapper.toEntity(salesmanToCreate));
        }catch (Exception e){
            log.error(e.getStackTrace().toString());
        }
        return salesmanToCreate;
    }

    @Override
    public SalesmanDto getById(long id) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(id);
            return salesmanMapper.toDto(salesman);
        }

    @Override
    public List<SalesmanDto> getAll() {
        return salesmanMapper.toDtoList(repo.findAll());
    }

    @Override
    public boolean deleteById(Long id) {
        Salesman salesman = repo.findSalesmanByDeleteDateIsNullAndId(id);

        if(salesman != null) {
            salesman.setDeleteDate(new Date(System.currentTimeMillis()));
            repo.save(salesman);
            return true;
        }
        return false;
    }



}
