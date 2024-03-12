package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepo extends JpaRepository<Buyer, Long> {
    Buyer findByDeleteDateIsNullAndId(Long id);

}
