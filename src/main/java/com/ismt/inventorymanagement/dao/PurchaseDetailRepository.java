package com.ismt.inventorymanagement.dao;

import com.ismt.inventorymanagement.entity.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail,Integer> {
}
