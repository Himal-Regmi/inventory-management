package com.ismt.inventorymanagement.dao;

import com.ismt.inventorymanagement.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail,Integer> {
}
