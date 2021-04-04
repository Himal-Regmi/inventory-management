package com.ismt.inventorymanagement.dao;

import com.ismt.inventorymanagement.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
}
