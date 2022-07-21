package com.example.shopg2.repository;

import com.example.shopg2.entity.QuantityInStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantityInStockRepository extends JpaRepository<QuantityInStock, String> {
}
