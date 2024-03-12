package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SalesmanRepo extends JpaRepository<Salesman, Long> {
    Salesman findSalesmanByDeleteDateIsNullAndId(Long id);

    List<Salesman> findAllByDeleteDateIsNull();
}
