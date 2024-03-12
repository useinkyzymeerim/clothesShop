package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {
        List<Transaction> findAllBySalesmanId(Long salesmanId);
        List<Transaction> findAllByBuyerId(Long id);

        Transaction findByDeleteDateIsNullAndId(Long id);

}
