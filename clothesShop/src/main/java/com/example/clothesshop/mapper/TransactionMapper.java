package com.example.clothesshop.mapper;

import com.example.clothesshop.dto.TransactionDTO;
import com.example.clothesshop.entity.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface TransactionMapper {
    TransactionDTO toDto(Transaction transaction);
    Transaction toEntity(TransactionDTO transactionDTO);
    List<TransactionDTO> toDtoList(List<Transaction> createTransaction);
}
