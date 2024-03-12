package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.TransactionDTO;
import com.example.clothesshop.entity.*;
import com.example.clothesshop.exeption.NotFoundExeption;
import com.example.clothesshop.mapper.TransactionMapper;
import com.example.clothesshop.repo.BuyerRepo;
import com.example.clothesshop.repo.ProductRepo;
import com.example.clothesshop.repo.SalesmanRepo;
import com.example.clothesshop.repo.TransactionRepo;
import com.example.clothesshop.service.ProductService;
import com.example.clothesshop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final BuyerRepo buyerRepo;
    private final ProductRepo productRepo;
    private final SalesmanRepo salesmanRepo;
    private final ProductService productService;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Salesman salesman = salesmanRepo.findById(transactionDTO.getSalesmanId())
                .orElseThrow(() -> new NotFoundExeption("Salesman not found with id " + transactionDTO.getSalesmanId()));

        Buyer buyer = buyerRepo.findById(transactionDTO.getBuyerId())
                .orElseThrow(() -> new NotFoundExeption("Buyer not found with id " + transactionDTO.getBuyerId()));

        Product product = productRepo.findById(transactionDTO.getProductId())
                .orElseThrow(() -> new NotFoundExeption("Product not found with id " + transactionDTO.getProductId()));

        try {
            transactionRepo.save(transactionMapper.toEntity(transactionDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return transactionDTO;
    }


    @Override
    public TransactionDTO findById(Long id) {
        Transaction transaction = transactionRepo.findByDeleteDateIsNullAndId(id);
        return transactionMapper.toDto(transaction);
    }

    @Override
    public List<TransactionDTO> getAll() {
        return transactionMapper.toDtoList(transactionRepo.findAll());
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = transactionRepo.findByDeleteDateIsNullAndId(id);

        if(transaction != null) {
            transaction.setDeleteDate(new Date(System.currentTimeMillis()));
            transactionRepo.save(transaction);
            return true;
        }
        return false;
    }

    @Override
    public void sellProduct(TransactionDTO transactionDTO, int quantity) {
        Salesman salesman = salesmanRepo.findById(transactionDTO.getSalesmanId())
                .orElseThrow(() -> new NotFoundExeption("Salesman not found with id" + transactionDTO.getSalesmanId()));
        Buyer buyer = buyerRepo.findById(transactionDTO.getBuyerId())
                .orElseThrow(() -> new NotFoundExeption("Buyer not found with Id" + transactionDTO.getBuyerId()));
        Product product = productRepo.findById(transactionDTO.getProductId())
                .orElseThrow(() -> new NotFoundExeption("Product not found with id" + transactionDTO.getProductId()));

        if (product.getQuantity() < transactionDTO.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock quantity");

        } else {
            Transaction transaction = new Transaction();
            transaction.setSalesman(salesman);
            transaction.setBuyer(buyer);
            transaction.setProduct(product);
            transaction.setQuantity(quantity);
            transactionRepo.save(transaction);
            productService.decreaseStockQuantity(product, quantity);

        }
    }
        @Override
        public List<Transaction> findAllBySalesmanId (Long id){
            return transactionRepo.findAllBySalesmanId(id);
        }

        @Override
        public List<Transaction> findAllByBuyerId (Long id){
            return transactionRepo.findAllByBuyerId(id);
        }


    public List<Product> getAllProductsOfSalesman(Long salesmanId) {  //4задание
        List<Transaction> transactions = transactionRepo.findAllBySalesmanId(salesmanId);
        return transactions.stream()
                .map(Transaction::getProduct)
                .toList();
    }

    public long getTotalProductsOfSalesman(Long salesmanId){  //5 задание
        List<Transaction> transactions = transactionRepo.findAllBySalesmanId(salesmanId);
        return transactions.stream()
                .mapToLong(Transaction::getQuantity)
                .sum();
    }

    public double getProductPriceOfSalesman(Long salesmanId, long productId){ // 6 задание
            Transaction transaction = transactionRepo.findAllBySalesmanId(salesmanId).stream()
                    .filter(t -> t.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundExeption("Product not found for salesman"));
            return transaction.getProduct().getPrice();
        }

    public  double getSpentBySalesman(Long salesmanId){ //7 задание
        List<Transaction> transactions = transactionRepo.findAllBySalesmanId(salesmanId);
          return  transactions.stream()
                  .mapToDouble(t -> t.getQuantity() * t.getProduct().getPrice())
                  .sum();

    }
     public List<Manufacturer> getAllManufacturerOfSalesmanProduct(Long salesmanId){ //8 задание
        List<Transaction> transactions = transactionRepo.findAllBySalesmanId(salesmanId);
        return transactions.stream()
                .map(t -> t.getProduct().getManufacturer())
                .distinct()
                .toList();
     }

     public List<String> productNameWithS(long salesmanId){ //9 задание
        List<Transaction> transactions = transactionRepo.findAllBySalesmanId(salesmanId);
        return transactions.stream()
                .map(t -> t.getProduct().getName() + "s")
                .toList();
     }

     public List<Integer> discountedPrice(Long salesmanId){ //10 задание
        List<Transaction> transactions = transactionRepo.findAllBySalesmanId(salesmanId);
        return transactions.stream()
                .mapToInt(t -> (int) (t.getProduct().getPrice() * 0.5))
                .boxed()
                .toList();
     }






}
