package com.springboot.asm.fpoly_asm_springboot.repository.primary;


import com.springboot.asm.fpoly_asm_springboot.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT new map(p.category.name as category, SUM(od.subtotal) as revenue) " +
            "FROM OrderDetail od " +
            "JOIN od.product p " +
            "GROUP BY p.category.name")
    List<Map<String, Object>> getRevenueByCategory();
}
