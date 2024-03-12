package com.example.clothesshop.service;

import com.example.clothesshop.dto.TransactionDTO;
import com.example.clothesshop.entity.Manufacturer;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.entity.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionDTO findById(Long id);

    List<TransactionDTO> getAll();
    void sellProduct(TransactionDTO transactionDTO, int quantity );
    boolean deleteById(Long id);
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    List<Transaction> findAllBySalesmanId(Long id);
    List<Transaction> findAllByBuyerId(Long id);
    List<Product> getAllProductsOfSalesman(Long salesmanId);
    long getTotalProductsOfSalesman(Long salesmanId);
    double getProductPriceOfSalesman(Long salesmanId, long productId);
    double getSpentBySalesman(Long salesmanId);
    List<Manufacturer> getAllManufacturerOfSalesmanProduct(Long salesmanId);
    List<String> productNameWithS(long salesmanId);
    List<Integer> discountedPrice(Long salesmanId);
}
