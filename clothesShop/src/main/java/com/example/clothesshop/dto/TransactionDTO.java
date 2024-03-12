package com.example.clothesshop.dto;

import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.entity.Salesman;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private Long id;
    private Long salesmanId;
    private Long buyerId;
    private Long productId;
    private Integer quantity;

}
