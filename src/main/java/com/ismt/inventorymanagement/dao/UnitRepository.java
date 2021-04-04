package com.ismt.inventorymanagement.dao;

import com.ismt.inventorymanagement.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Integer> {
}
